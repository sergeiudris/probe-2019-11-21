(ns datomicui.plugins.table.view
  (:require
   [re-frame.core :as re-frame]
   [re-com.core :as re-com]
   [re-pressed.core :as rp]
   [cljs.repl :refer [doc source]]
   [datomicui.subs :as subs]
   [re-frame-datatable.core :as dt]
   [re-frame-datatable.views :as dtv]
   [reagent.core :as r]
   [antizer.reagent :as ant]
   ))


(def log (.-log js/console))

(defn buttons []
  [:section
   [ant/button-group
    {:size     "small"
    ;  :on-click #(ant/message-info (.-value (-.target %1)))
    ;  :on-click #(prn (.-nativeEvent  %1))
    ;  :on-click #(log %1)
    ;  :on-click #(-> % .-target .-value log)
    ;  :on-click #(re-frame/dispatch [:get-entities (-> % .-target .-value)])
     :on-click #(re-frame/dispatch [:active-attribute (-> % .-target .-value)])}
    [ant/button {:value ":artist/name"} ":artist/name"]
    [ant/button {:value ":release/name"} ":release/name"]
    [ant/button {:value ":label/country"} ":label/country"]
    [ant/button {:value ":track/name"} ":track/name"]



    ; [ant/button {:value ":medium/format"} ":medium/format"]
    ; [ant/button {:value ":medium/tracks"} ":medium/tracks"]
    ]])

;; people data
(def people [{:id      1
              :name    "Tracey Davidson"
              :age     43
              :address "5512 Pockrus Page Rd"}
             {:id      2
              :name    "Pierre de Wiles"
              :age     41
              :address "358 Fermat's St"}
             {:id      3
              :name    "Lydia Weaver"
              :age     23
              :address "1251 Fourth St"}
             {:id      4
              :name    "Willie Reynolds"
              :age     26
              :address "2984 Beechcrest Rd"}
             {:id      5
              :name    "Richard Perelman"
              :age     51
              :address "2003 Poincaré Ricci Rd"}
             {:id      6
              :name    "Srinivasa Ramanujan"
              :age     32
              :address "1729 Taxi Cab St"}
             {:id      7
              :name    "Zoe Cruz"
              :age     31
              :address "8593 Pine Rd"}
             {:id      8
              :name    "Adam Turing"
              :age     41
              :address "1936 Automata Lane"}])

(defn comparison [data1 data2 field]
  (compare (get (js->clj data1 :keywordize-keys true) field)
           (get (js->clj data2 :keywordize-keys true) field)))


;; we need to use dataIndex instead of data-index, see README.md
(def columns [{:title     "Name"
               :dataIndex "name"
               :sorter    #(comparison %1 %2 :name)}
              {:title     "Age"
               :dataIndex "age"
               :sorter    #(comparison %1 %2 :age)}
              {:title     "Address"
               :dataIndex "address"
               :sorter    #(comparison %1 %2 :address)}])




(def pagination {:show-size-changer true
                 :default-page-size 10
                 :page-size-options ["5" "10" "20"]
                 :position          "top"
                 :show-total        #(str "Total: " % " entities")})

(defn add-actions-column [columns data-atom]
  (conj columns
        {:title  "Actions"
         :render #(r/as-element
                   [ant/button {:icon     "delete"
                                :type     "danger"
                                :on-click (fn []
                                            (reset! data-atom
                                                    (remove (fn [d] (= (get (js->clj %2) "id")
                                                                       (:id d))) @data-atom)))}])}))

(defn on-table-change [pagination filters sorter extra]
  (re-frame/dispatch [:entity-table-state pagination filters sorter extra]))

(defn table []
  "antd table to rednner people"
  (let [data-default         (r/atom people)
        data                 (re-frame/subscribe [:entities-vector])
        columns              (re-frame/subscribe [:entities-columns])
        entities-total-count (re-frame/subscribe [:entities-total-count])]
    (fn []
      ; (log (or data []))
      ; (log @data)
      ; (log @columns)
      (prn data)
      ; (log (into [] (or columns [])))
      [:div
       [:h2 "Entity Table"]
       [ant/table
        {:columns       @columns ;(add-actions-column columns data)
         :dataSource    @data
         :on-change     on-table-change
         :pagination    (merge pagination {:total     @entities-total-count
                                           :on-change #(log %1 %2)})
         :row-key       "id"
         :row-selection {:on-change #(let [selected (js->clj %2 :keywordize-keys true)]
                                       (ant/message-info (str "You have selected: " (map :name selected))))}}]])))



(defn view []
  [re-com/v-box
   :gap "1em"
   :children [[buttons]
              [table]
              ]])