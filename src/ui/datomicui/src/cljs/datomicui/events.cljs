(ns datomicui.events
  (:require
   [re-frame.core :as re-frame]
   [datomicui.db :as db]
   [day8.re-frame.tracing :refer-macros [fn-traced defn-traced]]
   [ajax.core :as ajax]
   [cljs.reader :as reader]
   [datomicui.dev :refer [conlog]]
   [vimsical.re-frame.cofx.inject :as inject]
   ))

(def default-db
  {:active-panel-key :home-panel
   :plugins          [{:datomicui/uuid (random-uuid)
                       :plugin/key     :plugin/info}
                      {:datomicui/uuid (random-uuid)
                       :plugin/key     :plugin/table}
                      {:datomicui/uuid (random-uuid)
                       :plugin/key     :plugin/text-search}]
   :active-tab-index 0
   :tabs/seq         0
   :entities-response nil
   :entity-table-state nil
   :flag true
   :tabs             [{:datomicui/uuid (random-uuid)
                       :tabs/seqid     0
                       :plugin/key     :plugin/info}]})

(comment

  (uuid "00000000-0000-0000-0000-000000000000")

  (random-uuid)
  ;
  )

(re-frame/reg-event-db
 ::initialize-db
 (fn-traced [_ _]
   default-db))

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
 :failed-response
 (fn [db [_ value]]
   (assoc db :entities-failed value)))


(comment
  
  *1
  )