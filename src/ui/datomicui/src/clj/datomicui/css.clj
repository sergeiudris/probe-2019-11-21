(ns datomicui.css
  (:require [garden.def :refer [defstyles]]))

(defstyles screen
  [:body {}]
  [:.level1 {:color "darkblue"}]
  [:.red {:color "red"}])
