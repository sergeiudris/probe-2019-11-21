(ns datomicui2.events
  (:require
   [re-frame.core :as re-frame]
   [datomicui2.db :as db]
   [day8.re-frame.tracing :refer-macros [fn-traced defn-traced]]
   [ajax.core :as ajax]
   [cljs.reader :as reader]
   [kit.core :refer [conlog]]
   [vimsical.re-frame.cofx.inject :as inject]
   [cljs.pprint :as pp]
   [datomicui2.subs :as subs]
   
   ))


(comment

  (uuid "00000000-0000-0000-0000-000000000000")

  (random-uuid)
  ;
  )

(re-frame/reg-event-db
 :ping
 (fn [db [_ value]]
   (assoc db :ping value)))

(re-frame/reg-event-db
 ::initialize-db
 (fn-traced [_ _]
   db/default-db))

(re-frame/reg-event-db
 ::set-active-panel-key
 (fn-traced [db [_ active-panel-key]]
   (assoc db :active-panel-key active-panel-key)))

(re-frame/reg-event-db
 ::set-re-pressed-example
 (fn [db [_ value]]
   (assoc db :re-pressed-example value)))

(re-frame/reg-event-db
 :failed-response
 (fn [db [_ value]]
   (assoc db :entities-failed value)))



(comment
  
  *1
  )