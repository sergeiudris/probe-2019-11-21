(ns datomicui.plugins.main.view
  (:require
   [re-frame.core :as re-frame]
   [re-com.core :as re-com]
   [re-pressed.core :as rp]
   [cljs.repl :refer [doc source]]
   [datomicui.subs :as subs]
   [re-frame-datatable.core :as dt]
   [re-frame-datatable.views :as dtv]
  ;  [cljsjs.antd]
  ;  [antd :as antd]
   [antizer.reagent :as ant]
   [reagent.core :as r]
   [datomicui.plugins.info.view]
   [datomicui.plugins.table.view]
   [datomicui.plugins.text-search.view]))



(comment
  
  (cljs.repl/dir r)
  
  

  ;
  )

