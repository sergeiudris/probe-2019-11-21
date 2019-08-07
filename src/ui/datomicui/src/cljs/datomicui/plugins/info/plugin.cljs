(ns datomicui.plugins.info.plugin
  (:require
   [re-frame.core :as re-frame]
   [re-com.core :as re-com]
   [re-pressed.core :as rp]
   [cljs.repl :refer [doc source]]
   [datomicui.subs :as subs]
   [re-frame-datatable.core :as dt]
   [re-frame-datatable.views :as dtv]
  ;  [cljsjs.antd]
  ;  [antd :as antd]
   [antizer.reagent :as ant]
   [reagent.core :as r]
   [cljs.pprint :as pp]
   [datomicui.plugins.info.core]
   [datomicui.plugins.table.core :as table-view]
   [datomicui.plugins.text-search.core]
   [kit.core :refer [keys-js]]
   ))


(defn text-search-results
  []
  (let [resp @(re-frame/subscribe [::subs/text-search-response])
        data (:data resp)]
    (into [:div]
          (map (fn [x]
                 (prn x)
                 [:div
                  [:span (first x)]
                  [:span (second x)]
                  [:span (nth x 2)]]) data))))

(defn info-tab
  []
  (let [resp @(re-frame/subscribe [::subs/db-names-response])
        data (:data resp)]
    [ant/select {:style {:min-width "128px"}}
     (map (fn [db-name]
            [ant/select-option  {:key db-name
                                 :value db-name} db-name]) data )]))



(def plugin
  {:tabui.plugins/uuid (random-uuid)
   :tabui.plugins/key :tabui.plugins/info
   :tabui.plugins/antd-icon "info-circle-o"
   :tabui.plugins/default-tab :tabui.tab/info-tab
   :tabui.plugins/containers [
                              ]
   :tabui.plugins/tabs [{:tabui.tab/uuid (random-uuid)
                         :tabui.tab/key :tabui.tab/info-tab
                         :tabui.tab/component info-tab
                         :tabui.container/key :tabui.main.container/center-container
                         }
                        ]})

(comment
 
 (keys plugin)
  (:tabui.plugins/key plugin)
 (cljs.pprint/pprint [1 23 ])
  
  (.-innerWidth js/window)
  
 )