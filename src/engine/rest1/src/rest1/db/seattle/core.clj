(ns rest1.db.seattle.core
  (:require [clj-time.core :as t]
            [clj-time.format :as f]
            [clojure.repl :refer :all]
            [clojure.data :refer :all]
            [clojure.pprint :as pp]
            [datomic.api :as d]
            [probe.tools.datomic.core :refer [q-idents q-attrs]]))




(comment

  (def db-uri "datomic:free://datomicfreedb:4334/seattle")

  (def conn (d/connect db-uri))

  (def db (d/db conn))

  (dir d)

  ;
  )


(comment

  
  (q-attrs db)

  ;
  )