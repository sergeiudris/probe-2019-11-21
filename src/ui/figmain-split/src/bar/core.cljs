(ns ^:figwheel-load foo.core
  (:require [goog.dom :as gdom]
            [goog.events :as events]
            [cljs.loader :as loader])
  (:import [goog.events EventType]))

(println "I'm foo!")

(defn listen-to-button []
  (let [app (gdom/getElement "app")
        button (gdom/createDom
                "button"
                (gdom/createTextNode "Load Bar!"))]
    (gdom/removeChildren app)
    (gdom/append app button)
    (events/listen button EventType.CLICK
                   (fn [e]
                     (loader/load :bar
                                  (fn []
                                    ((resolve 'bar.core/woz))))))))

(defonce init (listen-to-button))

(loader/set-loaded! :foo)