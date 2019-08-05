(ns rest1.db.datomicui.core
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

  (defonce db (d/db conn))

  (dir d)

  ;
  )


(defn q-paginted-entity
  "Returns entities and total count given limit,offset and attribute keword"
  [{:keys [db attribute limit offset]
    :or   {limit  10
           db  rest1.db.datomicui.core/db
           offset 0}}]
  {:entities (->>
              (d/q '{:find  [?e (count ?e)]
                     :in    [$ ?attribute]
                     :where [[?e ?attribute]]}
                   db attribute)
              (drop offset)
              (take limit)
              (map  #(identity (d/pull db '[*] (first %))))
              (into []))
   :count    (d/q '{:find  [(count ?e) .]
                    :in    [$ ?attribute]
                    :where [[?e ?attribute]]}
                  db attribute)})




(comment

  (q-attrs db)

  (q-idents db)

  (->
   (q-paginted-entity {; :db        db
                       :attribute :artist/name
                       :limit     3
                       :offset    0})
   pp/pprint)



  ;
  )