(ns hello-world.core
  (:require [clojure.repl :as r]
            ; [hello-world.goog-closure]
            [hello-world.goog-style])
  )

(defn hi
  []
  "hello")

(println "Hello world!")

(comment

  (js/console.log 3)

  (let [yay (js/yayQuery)]
    (.sayHello yay (.getMessage yay)))


  (let [yay ((goog.object.get js/window "yayQuery"))]
    ((goog.object.get yay "sayHello") ((goog.object.get yay "getMessage")))))
(.log js/console "hello-world.core loaded")





