(ns datomicui.plugins.main.plugin
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
   [datomicui.plugins.info.core]
   [datomicui.plugins.table.core :as table-view]
   [datomicui.plugins.text-search.core]))


(defn welcome-tab
  []
  [:div "datomicui"])

(defn dock-tab
  [{:keys [tab plugins]}]
 
    (as-> nil $
      (fn [plugin]
        (let [key (:tabui.plugins/key plugin)
              key-str (str (:tabui.plugins/key plugin))]
            ; (prn plugin)
          [ant/button {:key key-str
                       :size "small"
                       :value key-str
                       :on-context-menu (fn [e]
                                          (prn e.target.value)
                                          (prn plugin)
                                          )
                       :title   key-str}
           [ant/icon {:theme "outlined"
                      :type "info-circle"}]]
          ;
          ))
      (map $ plugins)
      (into [:div {:class "tabui-main-dock-list"}] $))
  ; [:div (str (:tabui.tab/key tab))]
  )

(def plugin
  {:tabui.plugins/uuid (random-uuid)
   :tabui.plugins/key :tabui.plugins/main
   :tabui.plugins/containers [{:tabui.container/uuid (random-uuid)
                               :tabui.container/key :tabui.main.container/dock-container
                               :tabui.container/style {}
                               :tabui.container/classes ["tabui-container tabui-main-dock-container"]}
                              {:tabui.container/uuid (random-uuid)
                               :tabui.container/key :tabui.main.container/center-container
                               :tabui.container/style {}
                               :tabui.container/classes ["tabui-container tabui-main-center-container"]}
                              {:tabui.container/uuid (random-uuid)
                               :tabui.container/key :tabui.main.container/panel-container
                               :tabui.container/style {}
                               :tabui.container/classes ["tabui-container tabui-main-panel-container"]}
                              {:tabui.container/uuid (random-uuid)
                               :tabui.container/key :tabui.main.container/header-container
                               :tabui.container/style {}
                               :tabui.container/classes ["tabui-container tabui-main-header-container"]}]
   :tabui.plugins/tabs [{:tabui.tab/uuid (random-uuid)
                         :tabui.tab/key :tabui.tab/welcome-tab
                         :tabui.tab/component welcome-tab}
                        {:tabui.tab/uuid (random-uuid)
                         :tabui.tab/key :tabui.tab/dock-tab
                         :tabui.tab/component dock-tab}]})

(comment
 
 (keys plugin)
  (:tabui.plugins/key plugin)
 
 )