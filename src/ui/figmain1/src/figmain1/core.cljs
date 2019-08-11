(ns figmain1.core
  (:require [clojure.repl :as repl]
            [probe.time.core :as pt]))


(.log js/console "Hello World!")

(defn hi
  []
  "hi")

(comment
  
  (repl/dir figmain1.core)
  
  (hi)
  
  (pt/ts)
  
  ;
  )