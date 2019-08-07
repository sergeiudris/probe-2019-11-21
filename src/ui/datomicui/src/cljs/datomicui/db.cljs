(ns datomicui.db
  (:require [datomicui.plugins.main.plugin]
            [datomicui.plugins.info.plugin]
            [datomicui.plugins.table.plugin]
            [datomicui.plugins.text-search.plugin]))

(def info-tab-uuid (random-uuid))
(def dock-tab-uuid (random-uuid))
(def text-search-tab-uuid (random-uuid))


(def default-db
  {:active-panel-key :home-panel
  ;  :plugins          [{:datomicui/uuid (random-uuid)
  ;                      :plugin/key     :plugin/info}
  ;                     {:datomicui/uuid (random-uuid)
  ;                      :plugin/key     :plugin/table}
  ;                     {:datomicui/uuid (random-uuid)
  ;                      :plugin/key     :plugin/text-search}]

   :plugins [datomicui.plugins.main.plugin/plugin
             datomicui.plugins.info.plugin/plugin
             datomicui.plugins.table.plugin/plugin
             datomicui.plugins.text-search.plugin/plugin
             ]
   :tab-instances [{:tabui.tab-instance/uuid dock-tab-uuid
                    :tabui.tab-instance/key :tabui.tab/dock-tab
                    :tabui.tab-instance/container-key :tabui.main.container/dock-container}
                   {:tabui.tab-instance/uuid (random-uuid)
                    :tabui.tab-instance/key :tabui.tab/settings-tab
                    :tabui.tab-instance/container-key :tabui.main.container/center-container}
                   {:tabui.tab-instance/uuid info-tab-uuid
                    :tabui.tab-instance/key :tabui.tab/info-tab
                    :tabui.tab-instance/container-key :tabui.main.container/center-container}
                   {:tabui.tab-instance/uuid text-search-tab-uuid
                    :tabui.tab-instance/key :tabui.tab/text-search-tab
                    :tabui.tab-instance/container-key :tabui.main.container/center-container}
                   ]
   :active-tabs {:tabui.main.container/center-container {:tabui.container/key :tabui.main.container/center-container
                                                         :tabui.tab-instance/uuid  text-search-tab-uuid}
                 :tabui.main.container/dock-container {:tabui.container/key :tabui.main.container/dock-container
                                                       :tabui.tab-instance/uuid  dock-tab-uuid}
                 }
   :active-tab-index 0
   :tabs/seq         0
   :entities-response nil
   :entity-table-state nil
   :context-menu-data nil
   :text-search-response nil
   :get-attrs-resp []
   :flag true
   :tabs             [{:datomicui/uuid (random-uuid)
                       :tabs/seqid     0
                       :plugin/key     :plugin/info}]})

(defn remove-tab-inst!
  [db tab-inst]
  ; (prn "tab inst" tab-inst)
  (let [tins (:tab-instances db)
        tins-filt
        (filter #(not= (:tabui.tab-instance/uuid %)
                       (:tabui.tab-instance/uuid tab-inst)) tins)]
    (assoc db :tab-instances tins-filt)))

(defn set-active-tab!
  [db container-key tab-inst-uuid]
  (let [active-tabs (:active-tabs db)
        next-tabs (assoc active-tabs container-key {:tabui.container/key container-key
                                                    :tabui.tab-instance/uuid tab-inst-uuid})]
    (assoc db :active-tabs next-tabs)))

(def context-menus
  {:datomicui.plugins.main.plugin/context-menu-dock-icon
   {:tabui.context-menu-uuk datomicui.plugins.main.plugin/context-menu-dock-icon}
   :datomicui.plugins.main.plugin/context-menu-header
   {:tabui.context-menu-uuk datomicui.plugins.main.plugin/context-menu-header}}
  )

(defn plugin->tab
 [plugin key]
 (let [tabs (:tabui.plugins/tabs plugin)]
   (->
    (filter #(= (:tabui.tab/key %) key) tabs)
    first)))

(defn new-default-tab-inst
  [plugin]
  (let [default-tab-key (:tabui.plugins/default-tab plugin)
        tab (plugin->tab plugin default-tab-key)]
    ; (prn "tab" tab)
    {:tabui.tab-instance/uuid (random-uuid)
     :tabui.tab-instance/key (:tabui.tab/key tab)
     :tabui.tab-instance/container-key (:tabui.container/key tab)}))

(defn add-new-default-tab-inst!
  [db plugin]
  (let [new-tab (new-default-tab-inst plugin)
        tab-insts (:tab-instances db)
        next-tab-insts (conj tab-insts new-tab)]
    ; (prn "new tab" new-tab)
    ; (prn next-tab-insts)
    ; (prn "tab-insts" tab-insts)
    ; (prn "next-tab-insts" next-tab-insts)
    (assoc db :tab-instances next-tab-insts)
    ))


(comment
 
  (uuid "00000000-0000-0000-0000-000000000000")
  
  (random-uuid)
  (keys default-db )
  (prn datomicui.plugins.main.plugin/context-menu-dock-icon)
  (:tabs default-db)
  ;
 )

