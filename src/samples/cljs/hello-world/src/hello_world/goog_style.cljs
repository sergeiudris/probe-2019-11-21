(ns hello-world.goog-style
  (:require [yq])
  )

(let [yay (js/yayQuery)]
  (.sayHello yay (.getMessage yay)))