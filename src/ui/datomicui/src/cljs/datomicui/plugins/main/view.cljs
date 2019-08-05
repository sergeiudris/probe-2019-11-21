(ns datomicui.plugins.main.view
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
   [datomicui.plugins.info.view]
   [datomicui.plugins.table.view :as table-view]
   [datomicui.plugins.text-search.view]))









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
    [:div {:style {:position "relative"
                   :left     0
                   :top      0
                   :height   "100vh"
                   :width    "50px"}}
     (map (fn [plugin]
            [ant/icon {:type  "plus"
                       :title (str (:plugin/key plugin))}]) @plugins)]))

(defn layout
  [props & children]
  [:section
   {:style {:padding "8px"}}
   children
   ]
  )

(defn main-panel []
  (let [active-panel-key (re-frame/subscribe [::subs/active-panel-key])]
    [layout
     {}
     [plugins-bar]
     [panels @active-panel-key]
     ]))

(comment
  
  (cljs.repl/dir r)
  
  

  ;
  )

