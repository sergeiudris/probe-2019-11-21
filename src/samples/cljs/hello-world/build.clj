(ns build
  (:require [clojure.repl]
            [cljs.build.api]))

; (clojure.repl/dir cljs.build.api)
; 
(cljs.build.api/build "src"
                      {:output-to "out/main.js"
                       :libs ["resources/yayquery-goog-style.js"]
                       :optimizations :advanced})



; (cljs.build.api/build "src"
;                       {:output-to "out/main.js"
;                        :externs ["yayquery-goog-style.js"]
;                        :foreign-libs [{:file "yayquery.js"
;                                        :provides ["yq"]}]
;                        :optimizations :advanced})