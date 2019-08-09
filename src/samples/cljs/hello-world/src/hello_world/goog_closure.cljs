(ns hello-world.goog-closure
  (:require [goog.dom :as dom]
            [goog.dom.classes :as classes]
            [goog.events :as events])
  (:import [goog Timer]))

(let [element (dom/createDom "div" "some-class" "Hello, World!")]
  (classes/enable element "another-class" true)
  (-> (dom/getDocument)
      .-body
      (dom/appendChild element))
  (doto (Timer. 1000)
    (events/listen "tick" #(.warn js/console "still here!"))
    (.start)))



