(ns main
  (:require [probe.tools.nrepl]
            [probe.tools.core]
            [probe.tools.io.core]
            [probe.tools.dgraph.core]
            [probe.tools.pedestal.server]
            [mbrainz.core]
   ;
            )
  ;
  )


(defn -dev  [& args]
  (probe.tools.nrepl/-main)
  ; (probe.tools.pedestal.server/run-dev)
  )

(defn -main  [& args]
  (probe.tools.nrepl/-main)
  )

(comment
  (probe.tools.core/try-parse-int "3")
  
  (probe.tools.core/version)
  
  
  ;
  )