(ns rest1.db.datomic.core
  (:require [clj-time.core :as t]
            [clj-time.format :as f]
            [clojure.repl :refer :all]
            [clojure.data :refer :all]
            [clojure.pprint :as pp]
            [datomic.api :as d]
            [probe.tools.datomic.core :refer [q-idents q-attrs]]))

(def db-uri "datomic:free://datomicfreedb:4334/mbrainz")

(declare conn)
(declare db)



(defn connect-lazy
  "Connect if conn does not exist"
  []
  (defonce conn (d/connect db-uri))
  (defonce db (d/db conn)))



(comment


  (defonce conn (d/connect db-uri))

  (def db (d/db conn))

  (dir d)

  ;
  )


(comment

  (q-attrs db)

  (q-idents db)


  ;
  )