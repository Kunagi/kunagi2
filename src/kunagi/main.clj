(ns kunagi.main
  (:require
   [appkernel.api :as app]

   [apptoolkit.http-server.mod]

   [kunagi.events]))


(defn -main [port]
  (tap> ::main)
  (app/assoc-in-db! [:http-server/port] (Integer/parseInt port))
  (app/start!))
