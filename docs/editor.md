
* browser editor:
   - lack intall plugins (because fetch every time)
   - lack file system access
   - cannot backup your .data in a git repo 
   - no access to launching processes

* electron editor:
    - cannot run from docker container

* docker + locahost editor:
    - problematic file system access
    + can save plugins and history
    + can run taks outside browser (say, loading a file)s
    - cross origin
    - still in browser


* windows vs tabs
    - tabs are btter:tabs bring structure, windows stimulate chaos
    - splitting screen in rectangles is enogh to drga between tabs

* what kind of ui matters:
    - plugin based, editor like, systemic
    - web apps serve a purpose but time+effort/outcome ration is subpar


# packaging data exploration plugin, visualizion, docker-compose

* you download some data, open code editor and clojure, start REPLing
* you query data, find answers youn needed. you are satisfied. code is the best ui
* but then, as a cherry on top, you need to visualize
* your options ? jupiter lab ..
* or you open editor and it creates cljs vizualizion plugin, you edit files and immediately see chages in editor
    so you are developing visualiztion for your the service that is running in your docker container
* now, you want to package it: you  select dedicated plugin, specify where to find your docker files.
  plugin generates plugin specific code (now you can use what you've done as a plugin) and also promts you with docker-compose file (you might edit or click ok)
  you can simply run your stack wtih docker-compose , and your plugin will open like an app
* then by means of plugin, you push to github


# datastore plugin

    * say, usda,record and other plugins are intended to use one datastore for the whole picture. one data.
    * then, i create datastore plugin, and other plugins use it as an abstrcation to store and query data
    * namespacing is key

# first datastore plugin

    * use datomicfree (plugin tab gives inforamtion, m b some funcitnoality)
    * datomic plugin for more fucntionality
    * record plugin (to log records)
    * datmoci graph vizualization plguin
    * usda also uses datomicfree datastore
    * machine learning plugins
    * package it!
