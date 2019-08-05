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
   [datomicui.plugins.table.view]
   [datomicui.plugins.text-search.view]))






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


;; main

(defn home-panel []
  [re-com/v-box
   :gap "1em"
   :children [[buttons]]])

(defn about-panel []
  [re-com/v-box
   :gap "1em"
   :children [[:div "about"]]])

(defn- panels [panel-name]
  (case panel-name
    :home-panel [home-panel]
    :about-panel [about-panel]
    [:div]))

(defn show-panel [panel-name]
  [panels panel-name])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [::subs/active-panel])]
    [re-com/v-box
     :height "100%"
     :children [[panels @active-panel]]]))

(comment
  
  (cljs.repl/dir r)
  
  

  ;
  )

