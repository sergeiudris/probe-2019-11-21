(ns kit.core
  (:require [cljs.repl :as repl]
            [cljs.pprint :as pp]
            ))

(defn keys-js
  [obj]
  ((.-keys js/Object) obj))


(comment
  
  (.-innerWidth js/window)
  
  ;
  )