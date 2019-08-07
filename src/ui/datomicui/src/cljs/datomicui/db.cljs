(ns datomicui.db
  (:require [datomicui.plugins.main.plugin]))


(def default-db
  {:active-panel-key :home-panel
  ;  :plugins          [{:datomicui/uuid (random-uuid)
  ;                      :plugin/key     :plugin/info}
  ;                     {:datomicui/uuid (random-uuid)
  ;                      :plugin/key     :plugin/table}
  ;                     {:datomicui/uuid (random-uuid)
  ;                      :plugin/key     :plugin/text-search}]

   :plugins [datomicui.plugins.main.plugin/plugin]
   :tab-instances [{:tabui.tab-instance/uuid (random-uuid)
                    :tabui.tab-instance/key :tabui.tab/welcome-tab
                    :tabui.tab-instance/container-key :tabui.main.container/center-container}
                  ;  {:tabui.tab-instance/uuid (random-uuid)
                  ;   :tabui.tab-instance/key :tabui.tab/dock-tab
                  ;   :tabui.tab-instance/container-key :tabui.main.container/dock-container
                  ;   }
                   ]
   :active-tab-index 0
   :tabs/seq         0
   :entities-response nil
   :entity-table-state nil
   :get-attrs-resp []
   :flag true
   :tabs             [{:datomicui/uuid (random-uuid)
                       :tabs/seqid     0
                       :plugin/key     :plugin/info}]})

(comment
 
  (uuid "00000000-0000-0000-0000-000000000000")
  
  (random-uuid)
  (keys default-db )
  
  (:tabs default-db)
  ;
 )

