(ns datomicui.css.tabui
  (:require [garden.def :refer [defstyles]]))

(defstyles tabui
  [:body {}]
  [:.tabui-level1 {:color "darkblue"}]
  [:.tabui-red {:color "red"}]
  [:.tabui-container {:border "1px solid black"}]
  [:.tabui-main-dock-container {:position "absolute"
                                :left 0
                                :top "16px"
                                :height "calc(100vh - 16px)"
                                :border-right "none"
                                :width "32px"}]
  [:.tabui-main-center-container {:position "absolute"
                                  :left "32px"
                                  :top "16px"
                                  :height "calc(100vh - 16px)"
                                  :width "calc(100vw - 32px)"}]
  [:.tabui-main-header-container {:position "absolute"
                                  :left 0
                                  :top 0
                                  :border-bottom "none"
                                  :height "16px"
                                  :width "100vw"}]
  [:.tabui-main-panel-container {:position "absolute"
                                 :left "32px"
                                 :bottom 0
                                 :height "256px"
                                 :width "calc(100vw - 32px)"}]
  [:.tabui-main-dock-list {:display "flex"
                           :flex-direction "column"
                           }]
  [:.tabui-tab-header {:border "1px solid grey"}]
  [:.tabui-tab-header-64 {:width "64px"
                          :height "16px"}]
  
  [:.anticon {
              ; :color "blue"
              }]
  [:.tabui-context-menu-option {:border "1px solid grey"
                                :cursor "pointer"
                                :background-color "#fff"
                                }]
  [:.tabui-context-menu-list {:position "fixed"
                             :display "flex"
                             :flex-direction "column"}]
  
  )
