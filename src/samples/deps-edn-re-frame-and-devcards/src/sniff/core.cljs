(ns sniff.core
  (:require  [reagent.core :as r]
             [cljs.repl :as repl]))

(defn simple-component []
  [:div
   [:p "I am a component!!"]
   [:p.someclass
    "I have " [:strong "bold"]
    [:span {:style {:color "red"}} " and red "] "text."]])

(defn ^:export main
  []
  (r/render [simple-component]
                  (.getElementById js/document "app")))

(def clog (.-log js/console))

(comment

  (ns sniff.core)
  
  (repl/dir cljs.repl)
  
  (clog 3)
  
  (->
   (js/fetch "http://localhost:7881/attrs")
   (.then (fn [r]
            (.json r)
            ))
   (.then (fn [r]
            (clog r)
            ))
   )

  ;
  )