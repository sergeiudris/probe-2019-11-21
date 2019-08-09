(ns build
  (:require [clojure.repl]
            [cljs.build.api]))

; (cljs.build.api/build "src"
;                       {:output-to "out/main.js"
;                        :libs ["resources/yayquery-goog-style.js"]
;                        :optimizations :advanced})


(cljs.build.api/build "src"
                      {:output-to "out/main.js"
                       :externs ["resources/yayquery-externs.js"]
                       :foreign-libs [{:file "resources/yayquery-foreign.js"
                                       :provides ["yq"]}]
                       :optimizations :advanced})