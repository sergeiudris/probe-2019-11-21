(ns figmain1.core
  (:require [clojure.repl :as repl]
            [probe.time.core :as pt]
            [figmain1.hooks]))

(goog-define some-val 3)

(.log js/console "Hello Live edit!!!!!")

(defn hi
  []
  "hi!")

(comment
  
  (repl/dir figmain1.core)
  
  (hi)
  
  (pt/ts)
  
  ;
  )