(ns tabui.core
  (:require [cljs.repl :as repl]
            [cljs.pprint :as pp]
            [datomicui.db :refer [default-db]]))


(defn find-tab
  [plugins tab-instance]
  (let []

    (->>
     (reduce (fn [acc x]
               (concat acc (:tabui.plugins/tabs x))) [] plugins)

     (filter (fn [t]
               (= (:tabui.tab-instance/key tab-instance) (:tabui.tab/key t))))
     (first)
     )
    ))


(comment
  
  (find-tab (:plugins default-db) {:tabui.tab-instance/key :tabui.tab/welcome-tab} )
  
  ;
  )