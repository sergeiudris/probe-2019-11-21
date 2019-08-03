(ns hello
  (:require [hello-time :as ht]
            [core.nrepl]))

(defn -main []
  ; (println "Hello world, the time is" (ht/time-str (ht/now)))
  (prn "starting nREPL 7888")
  (core.nrepl/-main))

(comment
  
  (println "Hello world, the time is" (ht/time-str (ht/now)))
  
  (ht/hi)
  ;
  )