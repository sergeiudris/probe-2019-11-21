(ns datomicui2.db
  (:require [cljs.repl]
            ))

(def info-tab-uuid (random-uuid))
(def dock-tab-uuid (random-uuid))
(def text-search-tab-uuid (random-uuid))


(def default-db
  {}
  )

(comment
 
  (uuid "00000000-0000-0000-0000-000000000000")
  
  (random-uuid)
  (keys default-db )
  (prn datomicui2.plugins.main.plugin/context-menu-dock-icon)
  (:tabs default-db)
  ;
 )

