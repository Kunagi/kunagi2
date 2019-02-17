(ns kunagi.main
  (:require
   [appkernel.api :as app]

   [apptoolkit.http-server.mod]

   [kunagi.events]))


(defn -main []
  (tap> ::main)
  (app/start! {}))
