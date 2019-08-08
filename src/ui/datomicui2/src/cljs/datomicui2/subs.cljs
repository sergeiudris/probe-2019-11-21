(ns datomicui2.subs
  (:require
   [re-frame.core :as re-frame]
   [cljs.repl :as repl]
   [cljs.pprint :as pp]
   [kit.core :refer [log-idty conlog]]
   ))


(re-frame/reg-sub
 ::active-panel-key
 (fn [db _]
   (:active-panel-key db)))



(re-frame/reg-sub
 ::re-pressed-example
 (fn [db _]
   (:re-pressed-example db)))



(comment
  
  (repl/doc get-in)
  
  (pp/pprint @re-frame.db/app-db)
  
  (-> js/window .-re_frame .-db .-app_db .-state)
  ;;window.re_frame.db.app_db.state

  
  )