(ns kunagi.figwheel-adapter
  (:require
   [apptoolkit.http-server.figwheel-adapter :as figwheel-adapter]

   [kunagi.main :as main]))

(def ring-handler-for-figwheel (figwheel-adapter/ring-handler-for-figwheel main/-main))
