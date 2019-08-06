(ns datomicui.plugins.main.core
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
   [datomicui.plugins.text-search.core]
   [tabui.core]
   ))




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
   :tabui.plugins/tabs []}
  )




;; main

(defn home-panel []
  [re-com/v-box
   :gap "1em"
   :children [[table-view/buttons]]])

(defn about-panel []
  [re-com/v-box
   :gap "1em"
   :children [[:div "about"]]])



(defn- panels [panel-name]
  (case panel-name
    :home-panel [home-panel]
    :table-panel [table-view/view]
    :about-panel [about-panel]
    [:div]))

(defn show-panel [panel-name]
  [panels panel-name])

; (defn main-panel []
;   (let [active-panel-key (re-frame/subscribe [::subs/active-panel-key])]
;     [re-com/v-box
;      :height "100%"
;      :children [[panels @active-panel-key]]]))

(defn plugins-bar
  []
  (let [plugins (re-frame/subscribe [::subs/plugins])]
    [:container {:key (Math/random)
           :style {:position "relative"
                   :left     0
                   :top      0
                   :height   "100vh"
                   :width    "50px"}}
     (map (fn [plugin]
            [ant/icon {:key (str (:plugin/key plugin))
                       :type  "plus"
                       :title (str (:plugin/key plugin))}]) @plugins)]))

(defn layout
  [props & children]
  [:section
   {:style {:padding "8px"}}
   children])

(defn main-panel0 []
  (let [active-panel-key (re-frame/subscribe [::subs/active-panel-key])]
    [re-com/v-box
    ;  :gap "1em"
     :style {:padding "16px"}
     :children [
                [plugins-bar]
                [panels @active-panel-key]]]))

(defn main-panel []
  (let [plugins (re-frame/subscribe [::subs/plugins])
        active-panel-key (re-frame/subscribe [::subs/active-panel-key])]
    ; [:div "datomicui"]
    [:div 
     (map (fn [container]
            [:section {:key (:tabui.container/key container)
                       :style (:tabui.container/style container) 
                       :class (->> (:tabui.container/classes container) (clojure.string/join " " )  )} ]
            ) (:tabui.plugins/containers plugin) )
     (map (fn [plugin]
            ; (prn plugin)
            [:button {:style {:position "absolute" :right 0}
                      :key (str (:tabui.plugins/key plugin))} (str (:tabui.plugins/key plugin)) ]
            ) @plugins)
     ]
    ))

(comment
  
  (cljs.repl/dir r)
  
  (Math/random)
  

  ;
  )

