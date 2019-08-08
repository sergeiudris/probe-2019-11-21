(ns datomicui2.core
  (:require
   [reagent.core :as reagent]
   [re-frame.core :as re-frame]
   [re-pressed.core :as rp]
   [datomicui2.events :as events]
   [datomicui2.routes :as routes]
  ;  [datomicui2.views :as views]
   [datomicui2.config :as config]
   [datomicui2.io :as io]
   [cljs.spec.alpha :as s]
   [goog.events :as gevents]
   [day8.re-frame.http-fx]
   
   ))


(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render
  ;  [views/main-panel]
   [:span ". So go ahead, try it out!"]
  ;  [main-view/main-panel]
   (.getElementById js/document "app")))

(defn ^:export init []
  (routes/app-routes)
  (re-frame/dispatch-sync [::events/initialize-db])
  (re-frame/dispatch-sync [::rp/add-keyboard-event-listener "keydown"])
  ; (re-frame/dispatch-sync [::events/set-active-panel :entity-panel])
  (dev-setup)
  (mount-root))

