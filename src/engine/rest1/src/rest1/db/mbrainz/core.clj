(ns rest1.db.mbrainz.core
  (:require [clj-time.core :as t]
            [clj-time.format :as f]
            [clojure.repl :refer :all]
            [clojure.data :refer :all]
            [clojure.pprint :as pp]
            [datomic.api :as d]))

(defn q-idents
  "find the idents of all schema elements in the system"
  [db]
  (sort (d/q '[:find [?ident ...]
               :where [_ :db/ident ?ident]]
             db)))


(defn q-attrs
  "find just the attributes"
  [db]
  (sort (d/q '[:find [?ident ...]
               :where
               [?e :db/ident ?ident]
               [_ :db.install/attribute ?e]]
             db)))


(comment

  (def db-uri "datomic:free://datomicfreedb:4334/mbrainz")

  (def conn (d/connect db-uri))

  (def db (d/db conn))

  (dir d)

  ;
  )


(comment

  (d/q '[:find ?id ?type ?gender
         :in $ ?name
         :where
         [?e :artist/name ?name]
         [?e :artist/gid ?id]
         [?e :artist/type ?teid]
         [?teid :db/ident ?type]
         [?e :artist/gender ?geid]
         [?geid :db/ident ?gender]]
       db
       "Janis Joplin")

  ;
  )