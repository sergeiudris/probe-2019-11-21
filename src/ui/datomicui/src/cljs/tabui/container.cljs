(ns tabui.container
  (:require [cljs.repl :as repl]
            [cljs.pprint :as pp]
            [tabui.core :refer [find-tab]]))



(defn container-comp
  [{:keys [tab-instances plugins container]} & children]
  [:section {:key (:tabui.container/key container)
             :style (:tabui.container/style container)
             :class (->> (:tabui.container/classes container) (clojure.string/join " "))}
   (->>
    (filter (fn [t]
              (= (:tabui.container/key container)  (:tabui.tab-instance/container-key t)))
            tab-instances)
    (map (fn [t]
           (let [tab (find-tab plugins t)
                 comp (:tabui.tab/component tab)]
              [comp {:key (:tabui.tab-instance/key t)
                     :plugins plugins
                     :tab tab}]
             ;
             ))))])

(defn containers-comp
  [{:keys [containers plugins tab-instances]} & children]
  [:div (map (fn [container]
               [tabui.container/container-comp {:key (:tabui.container/key container)
                                                :tab-instances tab-instances
                                                :plugins plugins
                                                :container container}])
             containers)])

(comment 
  
  (prn 3)
  
  (->
   (js/Promise. (fn [rslv,rjct]
                  (js/setTimeout #(prn "hi") 5000 )
                  (rslv 3)))
   (.then (fn [x]
            (prn "then " x)
            ))
   )
  
  (type (clojure.string/join " " ["1" "2"]))
  (prn (clojure.string/join " " ["1" "2"]))
  
  ;
  )