(ns kunagi.main
  (:require
   [appkernel.api :as app]

   [apptoolkit.http-server.mod]

   [kunagi.events]))


(defn -main []
  (app/start! {:app/name "kunagi"}))
