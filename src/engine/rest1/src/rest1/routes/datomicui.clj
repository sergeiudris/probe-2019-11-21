(ns rest1.routes.datomicui
  (:require [clojure.java.io :as io]
            [clojure.core.async :as async]
            [datomic.api :as d]
            [clojure.edn :as edn]
            [rest1.db.datomicui.core :as dtm]
            [rest1.db.starcraft.core]
            [rest1.db.seattle.core]
            [clojure.pprint :as pp]
            [probe.tools.datomic.core :refer [q-idents q-attrs]]
            [probe.tools.core :refer [try-parse-int]]
            [ring.util.response :as ring-resp]
            [clj-http.client :as client]
            [slingshot.slingshot :refer [throw+ try+]]
            [cheshire.core :as json]))

(defn get-attributes
  [request]
  (dtm/connect-lazy)
  (ring-resp/response (str (json/generate-string (q-attrs dtm/db)))))


(defn get-attributes-route
  [request]
  (get-attributes request))

(comment

  ;
  )


; ; http://localhost:8893/entity-params?limit=1&offset=0&attribute=%22:release/year%22&fmt=str
(defn get-entity-response [request]
  "Calls aq.query and sends edn as repsonse. Parses individual url string params (limit, offset, attribute etc.) "
  (let [{query-params :query-params} request
        {x :x
         limit :limit
         offset :offset
         attribute :attribute
         fmt :fmt
         :or {attribute ":artist/name"
              limit 10
              offset 0
              fmt "edn"}} query-params]
    {:status 200
     :body (let [body {:data (dtm/q-paginted-entity {:attribute (edn/read-string attribute)
                                                            :limit (or (try-parse-int limit) 10)
                                                            :offset (or (try-parse-int offset) 0)})
                       :query-params query-params
                       :random (Math/random)
                       :uuid (d/squuid)
                       :x x}]
             (if (= fmt "edn") body (str body)))}))



(defn get-entity-route [request]
  (get-entity-response request))


(comment
  
  (as-> nil x
    (try+
     (client/get "http://localhost:8080/datomicui/entity"
                 {:query-params {"attribute" ":artist/name"
                                 "limit" 3
                                 "offset" 0
                                 "fmt" "edn"
                                 }         
                  :headers      {}})
     (catch [:status 500] {:keys [request-time headers body]}
       (pp/pprint ; (json/parse-string body) 
        body))
     
     )
    (edn/read-string (:body x))
    (pp/pprint x)
    ; (do
      ; (prn (:status x))
      ; (pp/pprint (keys x))
      ; (pp/pprint (:cookies x))
      ; )
    )
  
  (as-> nil x
    (try+
     (client/post ""
                  {:body         (json/generate-string {:login    ""
                                                        :password ""})
                   :content-type :json
                   :accept       :json
                   :headers      {}})
     (catch [:status 500] {:keys [request-time headers body]}
       (pp/pprint ; (json/parse-string body) 
        body)))
    (do
      (prn (:status x))
      (pp/pprint (keys x))
      (pp/pprint (:cookies x))
      (pp/pprint (-> x :body json/parse-string (get "username")))))
  
  ;
  )



; (def gen-resp-entity-default-body {:attribute :artist/name
;                                    :limit 10
;                                    :offset 0
;                                    :fmt "edn"
;                                    :x 3})
; ;; http://localhost:8893/entity?data={:attribute
; (defn gen-resp-entity [request]
;   "Calls aq.query and sends edn as repsonse. Uses edn:  parses only one param 
; (edn/read-string (:data query-params)) "
;   (let [{query-params :query-params} request
;         {data-str :data
;          :or {data-str (str {:attribute :artist/name
;                              :limit 10
;                              :offset 0
;                              :fmt "edn"
;                              :x 3})}} query-params
;         data (merge gen-resp-entity-default-body (edn/read-string data-str))]
;     {:status 200
;      :body (let [body {:data (aq.query/get-paginted-entity data)
;                        :request-data data
;                        :random (Math/random)
;                        :uuid (d/squuid)}]
;              (if (= (:fmt data) "edn") body (str body)))}))



; (defn entity-params [request]
;   (gen-resp-entity-params request))


; (defn entity [request]
;   (gen-resp-entity request))