(ns datomicui.events
  (:require
   [re-frame.core :as re-frame]
   [datomicui.db :as db]
   [day8.re-frame.tracing :refer-macros [fn-traced defn-traced]]
   [ajax.core :as ajax]
   [cljs.reader :as reader]
   [datomicui.dev :refer [conlog]]
   [vimsical.re-frame.cofx.inject :as inject]
   [cljs.pprint :as pp]
   [datomicui.plugins.main.plugin]
   [datomicui.subs :as subs]
   
   ))



(comment

  (uuid "00000000-0000-0000-0000-000000000000")

  (random-uuid)
  ;
  )

(re-frame/reg-event-db
 :ping
 (fn [db [_ value]]
   (assoc db :ping value)))

(re-frame/reg-event-db
 ::initialize-db
 (fn-traced [_ _]
   db/default-db))

(re-frame/reg-event-db
 ::set-active-panel-key
 (fn-traced [db [_ active-panel-key]]
   (assoc db :active-panel-key active-panel-key)))

(re-frame/reg-event-db
 ::set-re-pressed-example
 (fn [db [_ value]]
   (assoc db :re-pressed-example value)))

(re-frame/reg-event-fx
 :get-entities
;  [(re-frame/)]
 [(re-frame/inject-cofx ::inject/sub [:entity-request-data])]
 (fn [{:keys [db entity-request-data]} [_ a]]
   (conlog entity-request-data)
   
   {:http-xhrio {:method :get
                 :uri "http://localhost:7881/datomicui/entity"
                 :response-format (ajax/raw-response-format)
                 :on-success [:process-response]
                 :format :edn
                 :params {:data (->> (merge {:limit 10 :offset 0 :attribute :artist/name :fmt "edn"} entity-request-data) str) }
                 :on-fail [:failed-response]}
    :db (assoc db :flag true)})
 )

(re-frame/reg-event-fx
 ::get-attrs
;  [(re-frame/)]
 [(re-frame/inject-cofx ::inject/sub [:get-attrs-resp])]
 (fn [{:keys [db attrs-req-data]} [_ a]]
   {:http-xhrio {:method          :get
                 :uri             "http://localhost:7881/datomicui/attrs"
                 :response-format (ajax/raw-response-format)
                 :on-success      [:handle-get-attrs-resp]
                 :format          :edn
                 :params          {}
                 :on-fail         [:failed-response]}
    :db         db}))

(re-frame/reg-event-fx
 :entity-table-state
 (fn [{:keys [db]} [_ pagination filters sorter extra]]
  ;  (prn value)
   {
    :dispatch [:get-entities nil]
    :db    (assoc db :entity-table-state {:pagination  (js->clj pagination :keywordize-keys true)
                                          :filters (js->clj filters :keywordize-keys true)
                                          :sorter (js->clj sorter :keywordize-keys true)
                                          :extra (js->clj extra :keywordize-keys true)})}
   ))

(re-frame/reg-event-fx
 :active-attribute
 (fn [{:keys [db]} [_ value]]
   {
    :dispatch [:get-entities value]
    :db (assoc db :active-attribute value)
    }
   )
 )


; {:pagination pagination
;  :filters filters
;  :sorter sorter
;  :extra extra}

(re-frame/reg-event-db
 :process-response
 (fn [db [_ value]]
  ;  (prn value)
   (assoc db :entities-response (reader/read-string value) )
   ))



(re-frame/reg-event-db
 :handle-get-attrs-resp
 (fn [db [_ value]]
  ;  (prn value)
   (assoc db :get-attrs-resp (reader/read-string value))))

(re-frame/reg-event-db
 :failed-response
 (fn [db [_ value]]
   (assoc db :entities-failed value)))

(re-frame/reg-event-fx
 :open-context-menu
 (fn [{:keys [db]} [_ eargs]]
   (prn ":open-context-menu")
   (pp/pprint (:event eargs))
   (let [k (:tabui.context-menu-uuk eargs)
         menu-fn (get-in db/context-menus [k :tabui.context-menu-uuk])]
    ;  (prn "k is " k)
    ;  (prn eargs)
     {:dispatch [:ping (Math/random)]
      :db (assoc db :context-menu-data {:menu (menu-fn {})
                                        :eargs eargs})})))

(re-frame/reg-event-fx
 :select-tab
 (fn [{:keys [db]} [_ eargs]]
   (prn ":select-tab")
   (prn eargs)
   (let [k (:tabui.context-menu-uuk eargs)
         tab-inst (:tab-inst eargs)
         container (:container eargs)
         container-key (:tabui.container/key container)
         tab-inst-uuid (:tabui.tab-instance/uuid tab-inst)
         ]
     {:dispatch [:ping (Math/random)]
      :db (db/set-active-tab! db container-key tab-inst-uuid) 
      ; :db db
      }
     )))

(re-frame/reg-event-fx
 :select-menu-option
 [(re-frame/inject-cofx ::inject/sub [::subs/context-menu-data])]
 (fn [{:keys [db ctx-menu-data]} [_ eargs]]
   (prn ":select-menu-option")
   (let [option (:option eargs)
         menu-eargs (:menu-eargs eargs)
         tab-inst (:tab-inst menu-eargs)
         k (:key option)]
    ;  (prn option)
    ;  (prn "k is " k)
    ;  (prn eargs)
    ;  (pp/pprint "menu-eargs" menu-eargs )
    ;  (prn menu-eargs)
     {:dispatch [:ping (Math/random)]
      :db (case k
            :tabui.plugins.main/open-tab-inst (merge db
                                             (db/remove-tab-inst! db tab-inst)
                                             {:context-menu-data nil})
            :tabui.plugins.main/close-tab-inst (merge db
                                             (db/remove-tab-inst! db tab-inst)
                                             {:context-menu-data nil})
            (merge db {:context-menu-data nil}))})))

(re-frame/reg-event-db
 :close-context-menu
 (fn [db [_ value]]
   (prn ":close-context-menu")
   (assoc db :context-menu-data nil)))


(comment
  
  *1
  )