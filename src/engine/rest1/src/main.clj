(ns main
  (:require [probe.tools.nrepl]
            [probe.tools.core]
            [probe.tools.io.core]
            [probe.tools.dgraph.core]
            [rest1.service.server]
   ;
            )
  ;
  )


(defn -dev  [& args]
  (probe.tools.nrepl/-main)
  (rest1.service.server/run-dev)
  )

(defn -main  [& args]
  (probe.tools.nrepl/-main)
  )

(comment
  
  (probe.tools.core/try-parse-int "3")
  
  (probe.tools.core/version)
  
  
  ;
  )