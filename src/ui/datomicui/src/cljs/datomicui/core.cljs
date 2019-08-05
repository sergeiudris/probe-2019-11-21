(ns datomicui.core
  (:require
   [reagent.core :as reagent]
   [re-frame.core :as re-frame]
   [re-pressed.core :as rp]
   [datomicui.events :as events]
   [datomicui.routes :as routes]
  ;  [datomicui.views :as views]
   [datomicui.config :as config]
   [datomicui.io :as io]
   [cljs.spec.alpha :as s]
   [goog.events :as gevents]
   [day8.re-frame.http-fx]
   [datomicui.plugins.main.view :as main-view]
   
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
   (.getElementById js/document "app")))

(defn ^:export init []
  (routes/app-routes)
  (re-frame/dispatch-sync [::events/initialize-db])
  (re-frame/dispatch-sync [::rp/add-keyboard-event-listener "keydown"])
  (re-frame/dispatch-sync [::events/set-active-panel :entity-panel])
  (dev-setup)
  (mount-root))

