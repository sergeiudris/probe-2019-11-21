(ns sniff.devcards
  (:require [devcards.core :refer-macros (defcard)]
            [devcards.core :as dc]
            [sablono.core :as sab]
            [reagent.core :as r]))

(defonce first-example-state (atom {:count 55}))



(defcard simple-component
  "dis some **markdown** docs"
  (fn [data-atom owner]
    (sab/html
     [:div
      [:p  "I am a component! "  (:count @data-atom) ]
      [:p.someclass
       "I have " [:strong "bold"]
       [:span {:style {:color "red"}} " and red "] "text."]]))
  first-example-state
  {:heading false})

(defcard
  example-counter
  (fn [data-atom owner]
    (sab/html
     [:h3
      "Example Counter w/Shared Initial Atom: "
      (:count @data-atom)]))
  first-example-state)

(defcard
  example-incrementer
  (fn [data-atom owner]
    (sab/html
     [:button
      {:onClick (fn [] (swap! data-atom update-in [:count] inc))}
      "increment"]))
  first-example-state)

(defcard
  example-decrementer
  (fn [data-atom owner]
    (sab/html
     [:button
      {:onClick (fn [] (swap! data-atom update-in [:count] dec))}
      "decrement"]))
  first-example-state)

(defn ^:export main
  []
  (dc/start-devcard-ui!))

