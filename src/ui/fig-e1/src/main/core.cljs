(ns main.core
  (:require ["electron" :as e :refer (app BrowserWindow)]
            ["path" :as path]
            ["url" :as url]
            ["child_process" :as cp :refer (exec)]
            [clojure.string]))

(defonce win-ref (atom nil))

(defn create-window []
  (let [win
        (BrowserWindow. #js {:width 800
                             :height 600
                             "webPreferences" {
                                             "nodeIntegration" true  
                                              }
                             })

        url
        (url/format #js {:pathname (path/join js/__dirname "index.html")
                         :protocol "file:"
                         :slashes true})]

    (.loadURL win url)

    (.. win -webContents (openDevTools))

    (reset! win-ref win)
    
    (.on win "closed"
      (fn [e]
        (reset! win-ref nil)))))

(defn maybe-quit []
  (when (not= js/process.platform "darwin")
    (.quit app)))

(defn maybe-create-window []
  (when-not @win-ref
    (create-window)))

(defn main []
  (.on app "ready" create-window)
  (.on app "activate" maybe-create-window)
  (.on app "window-all-closed" maybe-quit))


(defn execute
  [cmd]
  (exec cmd #js {:shell "/bin/bash"}
        (fn [err stdout stderr]
          (cond
            err (prn stderr)
            :else (prn (clojure.string/replace stdout \newline \space)) ))))

(main)

(comment
  (prn 3)
  (prn js/process.platform)
  
  js/process.versions.node
  js/process.versions.chrome
  js/process.versions.electron
  
  (exec "pwd" (fn [err stdout stderr]
                (prn stdout)
                ))
  
  (exec "docker ps" (fn [err stdout stderr]
                (prn stdout)))
  
  (execute "pwad")
  
  
  (execute "pwd")
  
  (execute "docker ps")
  
  (execute "pushd ~/code/probe; sh c up ")
  
  (execute "pushd ~/code/probe; sh c down ")
  
  (execute "ls")
  
  
  )