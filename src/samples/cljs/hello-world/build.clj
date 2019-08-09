(ns build
  (:require [clojure.repl])
  )

(clojure.repl/dir cljs.build.api)

(prn "hi")

(cljs.build.api/build "src"
                      {:output-to "out/main.js"
                       :externs [;"yayquery-externs.js"
                                 "yayquery-goog-style.js"]
                       :optimizations :advanced})