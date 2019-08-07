(ns datomicui.plugins.text-search.plugin
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
   [kit.core :refer [keys-js]]))




(defn text-search-tab
  []
  ; [:div "info: text search"]
  [:div
   [ant/input-search
    {:on-search (fn [e]
                  (prn e)
                  (re-frame/dispatch [:text-search e ])
                  )}
    ]
   ]
  
  )



(def plugin
  {:tabui.plugins/uuid (random-uuid)
   :tabui.plugins/key :tabui.plugins/text-search
   :tabui.plugins/antd-icon "search"
   :tabui.plugins/default-tab :tabui.tab/text-search-tab
   :tabui.plugins/containers []
   :tabui.plugins/tabs [{:tabui.tab/uuid (random-uuid)
                         :tabui.tab/key :tabui.tab/text-search-tab
                         :tabui.tab/component text-search-tab
                         :tabui.container/key :tabui.main.container/center-container}]})

(comment

  (keys plugin)
  (:tabui.plugins/key plugin)
  (cljs.pprint/pprint [1 23])

  (.-innerWidth js/window))