
* there are plugins
* there are tabs
* there are containers

* containers contain tabs
* they are boxes, there are default ones and a plugin can add containers or add tabs to existing (by key)
* it should be transparent: when you right a plugin consisting of one tab, you want to say: in this mode it goes to 'sidebar' container, else it goes to main container, for example

* tabs , tabs everywhere
* tabs have ids and keys, can emit events and subscrie to global state, which stores all tabs data as well
* hwen created, tabs finds other tabs it is supposed to interact with
* tab gets context (app's db) and it's own db (which is a proxy to a part of app's db
* tab can emit events to change it's own state and other tabs' states

* plugin has containers and tabs and state
* when you create  a plugin, you can say 'here are target containers', if they exist, tabs will be added to them as tabs,
otherwise they will be added. you say 'here are tabs', they react to these events, they emit these events.
* plugins have permissions , so user sees what plugin can change

* main plugin holds default containers and some tabs

* user can stretch, change containers. and user can even say  'you know, this plugin uses these containers for its tabs. i want it to use these instread'. or add new container

* containers and tabs should be declared in edn in docker-compose like style, with depends_on , interacts_with etc.

* tabs, tabs everywhere

* when you pin a tab (contextmenu), it cannot be closed (to prevent accidental closing)
* when you drag a tab from one container to another, you can specify (with contextmenu) whether it's just for this one time fot his tab or you mean that pligin should always use this container from now on

* all settings are stored


* if some ui logic does not fit into tab simplicity, first consider the use case, validity of the logic. systemic approach is better. proven by the browser, the editor and the linux interfaces.

* tabs should support backforward browser-like navigation, where URI is pointing to plugin's tab. new tab is rendered in the same tab-instance

* context-menu on anything should give a list of options, one of which inforamtion - opens a tab with plugin/tab/container, value etc. info

--

* first, main plugin containers are being rendered, then its tabs; then the rest; when main's container is being rendered, it goes 
over all plugins (if they are active for example, maybe a control mechanism ) and adds their tabs that also target this container

# loading plugin 
* plugin is app/lib: it exposes id, containers, tabs.
* when loaded into the editor, code gets evaluated and id etc. are added to db
* plugin gets added to dock for example
* then init is run on plugin, it emits events
* when open tab is clicked, editor finds plugin's tab and renders it
* there is an option to preload plugins in the background after initial load (which should be fast)


* plugins can be written in any language
* tabui/plugin-tools package exposes clojuscript and js api; it contains reagent (react), re-frame and toher tools;
  it exposes them via interface (docs are auto generated w/ codox) and plugin creator uses plugin-tools api to emit events, subscribe and read store's state
* so plugin can be in cljs, js, ts, elm, reason or any other language then has js interperability and compiles to js
* plugin-tools should be called 'plugin-kit'


# render

* put data strucutre in db plugins-containers/tabs
* go over main plugins containers, pass them to container component (along with context)
* containers render open tabs from db/open-tabs [tab-instance]
* by default main/plugin-list main/welcome-page are open
* when plugin is clicked, tab-instance is added to sv/open-tabs with target container taken from db/plugin/tabs/tab 
* when tab-instance is moved, db/open-tabs/tab-instance/target-container is chnaged to new target
* main/dock main/center main/panel will be starting main containers
* main/dock will  db/open-tabs/tab-instance main/plugin-list and render it

# things that are a plugin

* authentication is a plugin. you always can load the editor, but to authenticate to one of the ssytems, you load 'example.com/auth' plugin and log-in
  then other plugins of example.com can use token that is in editors db

* list of plugins (editor has an official list of plugins that are edn stored in a repo)
  list-of-plugins plugin fetches the edn and you can install plugins.
  list contains uri where you can download precompiled plugin

# localStorage

* localStorage should be protected: you cannot forbid it, but editor should infer (somehow) when a plugin changes localStorage directly and warn the user that
  'plugin x directly changes localStorage, which is anti-pattern' 
