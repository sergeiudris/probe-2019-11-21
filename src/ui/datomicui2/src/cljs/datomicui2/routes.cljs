(ns datomicui2.routes
  (:require-macros [secretary.core :refer [defroute]])
  (:import goog.History)
  (:require
   [secretary.core :as secretary]
   [goog.events :as gevents]
   [goog.history.EventType :as EventType]
   [re-frame.core :as re-frame]
   [re-pressed.core :as rp]
   [datomicui2.events :as events]
   ))

(defn hook-browser-navigation! []
  (doto (History.)
    (gevents/listen
     EventType/NAVIGATE
     (fn [event]
       (secretary/dispatch! (.-token event))))
    (.setEnabled true)))

(defn app-routes []
  (secretary/set-config! :prefix "#/")
  ;; --------------------
  ;; define routes here
  (defroute "/" []
    (re-frame/dispatch [::events/set-active-panel-key :table-panel])
    (re-frame/dispatch [::events/set-re-pressed-example nil])
    (re-frame/dispatch
     [::rp/set-keydown-rules
      {:event-keys [[[::events/set-re-pressed-example "Hello, world!"]
                     [{:which 72} ;; h
                      {:which 69} ;; e
                      {:which 76} ;; l
                      {:which 76} ;; l
                      {:which 79} ;; o
                      ]]]

       :clear-keys
       [[{:which 27} ;; escape
         ]]}])
    )

  (defroute "/about" []
    (re-frame/dispatch [::events/set-active-panel-key :about-panel]))

  ; (+ 1 1)
  
  (defroute "/home" []
    (re-frame/dispatch [::events/set-active-panel-key :home-panel]))
  
  (defroute "/table" []
    (re-frame/dispatch [::events/set-active-panel-key :table-panel]))
  
  
  ;; --------------------
  (hook-browser-navigation!))
