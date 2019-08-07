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




(defn welcome-tab
  []
  [:div "info: welcome"])



(def plugin
  {:tabui.plugins/uuid (random-uuid)
   :tabui.plugins/key :tabui.plugins/info
   :tabui.plugins/antd-icon "info-circle-o"
   :tabui.plugins/default-tab :tabui.tab/welcome-tab
   :tabui.plugins/containers [
                              ]
   :tabui.plugins/tabs [{:tabui.tab/uuid (random-uuid)
                         :tabui.tab/key :tabui.tab/welcome-tab
                         :tabui.tab/component welcome-tab
                         :tabui.container/key :tabui.main.container/center-container
                         }
                        ]})

(comment
 
 (keys plugin)
  (:tabui.plugins/key plugin)
 (cljs.pprint/pprint [1 23 ])
  
  (.-innerWidth js/window)
  
 )