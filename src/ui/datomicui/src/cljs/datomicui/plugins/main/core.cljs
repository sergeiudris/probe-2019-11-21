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
   [tabui.container ]
   [datomicui.plugins.main.plugin :refer [plugin]]
   
   ))






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


(defn random-button-comp
  [{:keys [plugin]} & children]
  (let [key (:tabui.plugins/key plugin)
        key-str (str key)]
    [:button {:style {:position "absolute" :right 0}
              :value key-str} key-str]))

(defn random-buttons-comp
  [{:keys [plugins]} & children]
  (as-> nil $
    (fn [plugin]
            ; (prn plugin)
      [random-button-comp {:key (:tabui.plugins/key plugin)
                         :plugin plugin}]
      )
    (map $ plugins)
    (into [:div] $)))

(defn main-panel []
  (let [plugins @(re-frame/subscribe [::subs/plugins])
        tab-instances  @(re-frame/subscribe [::subs/tab-instances])
        active-panel-key (re-frame/subscribe [::subs/active-panel-key])]
    ; [:div "datomicui"]
    [:div
     [tabui.container/containers-comp {:tab-instances tab-instances
                                      :plugins plugins
                                      :containers (:tabui.plugins/containers plugin)}]
     [random-buttons-comp {:plugins plugins}]
     ]))

(comment
  
  (cljs.repl/dir r)
  
  (Math/random)
  
  (def $ 3)

  ;
  )

