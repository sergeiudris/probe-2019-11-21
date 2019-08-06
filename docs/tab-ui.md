
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

