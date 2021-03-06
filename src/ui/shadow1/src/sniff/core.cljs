(ns sniff.core
  (:require  [reagent.core :as r]
             [cljs.repl :as repl]
             [cljs.pprint :as pp]
             [probe.time.core :as pt]))

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
  
  (repl/dir pt)
  
  (pt/hi)
  
  (clog (pt/hi))
  
  (clog 3)
  
  (->
   (js/fetch "http://localhost:7881/attrs")
   (.then (fn [r]
            (.json r)
            ))
   (.then (fn [r]
            ; (clog r)
            (prn r)
            ))
   )
  
  (pp/pprint 3)

  ;
  )