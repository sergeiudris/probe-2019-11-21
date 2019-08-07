(ns datomicui.plugins.table.core
  (:require
   [re-frame.core :as re-frame]
   [re-com.core :as re-com]
   [re-pressed.core :as rp]
   [cljs.repl :refer [doc source]]
   [datomicui.subs :as subs]
   [re-frame-datatable.core :as dt]
   [re-frame-datatable.views :as dtv]
   [reagent.core :as r]
   [antizer.reagent :as ant]
   ))




(defn view []
  [re-com/v-box
   :gap "1em"
   :children [[buttons]
              [table]
              ]])