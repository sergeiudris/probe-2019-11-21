(ns nba.core
  (:require [clojure.repl :refer :all]
            [ala.print :refer [cprn]]
            [nba.api :refer []]))




(comment

  (nba.api/scoreboard)
  (nba.api/shotchartdetail)
  

  (def res (nba.api/scoreboard))
  

  

  ;;;
  )
