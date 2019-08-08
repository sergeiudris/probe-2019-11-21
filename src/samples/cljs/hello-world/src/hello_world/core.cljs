(ns hello-world.core)

(defn hi
  []
  "hello")

(println "Hello world!")

(comment

  (js/console.log 3)
  
  )


(.log js/console "hello-world.core loaded")
