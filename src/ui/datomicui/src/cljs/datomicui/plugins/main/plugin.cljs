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
   [cljs.pprint :as pp]
   [datomicui.plugins.info.core]
   [datomicui.plugins.table.core :as table-view]
   [datomicui.plugins.text-search.core]
   [kit.core :refer [keys-js]]
   ))




(defn settings-tab
  []
  [:div "main: settings"])

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
                                          ; (prn e.target.value)
                                          (.persist e)
                                          (.preventDefault e)
                                          ; (prn (keys-js e))
                                          ; (pp/pprint (keys-js (.-nativeEvent e)))
                                          ; (pp/pprint (.-clientX e))
                                          ; (prn plugin)
                                          (re-frame/dispatch
                                           [:open-context-menu
                                            {:event {:clientX (.-clientX e)
                                                     :clientY (.-clientY e)}
                                             :tabui.context-menu-uuk
                                             :datomicui.plugins.main.plugin/context-menu-dock-icon
                                             :tab tab
                                             :plugin plugin}]))
                       :title   key-str}
           [ant/icon {:theme "outlined"
                      :type (:tabui.plugins/antd-icon plugin)}]]
          ;
          ))
      (map $ plugins)
      (into [:div {:class "tabui-main-dock-list"}] $))
  ; [:div (str (:tabui.tab/key tab))]
  )

(defn context-menu-dock-icon
  [ctx]
  [{:key :tabui.plugins.main/info
    :title "info"}
   {:key :tabui.plugins.main/open-tab-inst
    :title "open tab"}])

(defn context-menu-header
  [ctx]
  [{:key :tabui.plugins.main/close-tab-inst
    :title "close"}
   {:key :tabui.plugins.main/pin-tab-inst
    :title "pin"}])

(def plugin
  {:tabui.plugins/uuid (random-uuid)
   :tabui.plugins/key :tabui.plugins/main
   :tabui.plugins/antd-icon "setting"
   
   :tabui.plugins/containers [{:tabui.container/uuid (random-uuid)
                               :tabui.container/key :tabui.main.container/dock-container
                               :tabui.container/style {}
                               :tabui.container/classes ["tabui-container tabui-main-dock-container"]}
                              {:tabui.container/uuid (random-uuid)
                               :tabui.container/key :tabui.main.container/center-container
                               :tabui.container/style {}
                               :tabui.container/classes ["tabui-container tabui-main-center-container"]
                               :tabui.container/header-list-classes ["tabui-container-main-header-list "]
                               :tabui.container/header-classes ["tabui-container-header tabui-container-main-header "]
                               :tabui.container/content-classes ["tabui-container-main-content "]
                               }
                              {:tabui.container/uuid (random-uuid)
                               :tabui.container/key :tabui.main.container/panel-container
                               :tabui.container/style {}
                               :tabui.container/classes ["tabui-container tabui-main-panel-container"]}
                              {:tabui.container/uuid (random-uuid)
                               :tabui.container/key :tabui.main.container/header-container
                               :tabui.container/style {}
                               :tabui.container/classes ["tabui-container tabui-main-header-container"]}]
   :tabui.plugins/default-tab :tabui.tab/settings-tab
   :tabui.plugins/tabs [
                        {:tabui.tab/uuid (random-uuid)
                         :tabui.tab/key :tabui.tab/dock-tab
                         :tabui.container/key :tabui.main.container/dock-container
                         :tabui.tab/component dock-tab}
                        {:tabui.tab/uuid (random-uuid)
                         :tabui.container/key :tabui.main.container/center-container
                         :tabui.tab/key :tabui.tab/settings-tab
                         :tabui.tab/component settings-tab}
                        ]})

(comment
 
 (keys plugin)
  (:tabui.plugins/key plugin)
 (cljs.pprint/pprint [1 23 ])
  
  (.-innerWidth js/window)
  
 )