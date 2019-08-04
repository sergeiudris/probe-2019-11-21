(ns wui.dev
  (:require [cljs.repl :as repl]
            [cljs.pprint :as pp])
  )


(defn log-idty [result]
  (prn result)
  result
  )

(def clog (.-log js/console))

(defn conlog [v]
  ((.-log js/console) v)
  v
  )


(comment
  (->> (range 100)
       (take 20)
       (drop 5)
       log-idty
       reverse
       log-idty)
  
  
  )

(comment


  (repl/dir cljs.repl)

  (clog 3)

  (->
   (js/fetch "http://localhost:7881/attrs")
   (.then (fn [r]
            (.json r)))
   (.then (fn [r]
            (clog r))))
  
  (->
   (js/fetch "http://localhost:7881/attrs")
   (.then (fn [r]
            (.json r)))
   (.then (fn [r]
            (prn r))))

  ;
  )