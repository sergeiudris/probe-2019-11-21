(ns tabui.container
  (:require [cljs.repl :as repl]
            [cljs.pprint :as pp]))



(def default-containers
  {:dock-container
   :main-container
   :panel-container
   
   }
  
  )

(comment 
  
  (prn 3)
  
  (->
   (js/Promise. (fn [rslv,rjct]
                  (js/setTimeout #(prn "hi") 5000 )
                  (rslv 3)))
   (.then (fn [x]
            (prn "then " x)
            ))
   )
  
  
  ;
  )