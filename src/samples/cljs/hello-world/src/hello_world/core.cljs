(ns hello-world.core
  (:require [clojure.repl :as r])
  )

(defn hi
  []
  "hello")

(println "Hello world!")

(comment

  (js/console.log 3)
  
  )


(.log js/console "hello-world.core loaded")
