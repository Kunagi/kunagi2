(ns kunagi.main
  (:require
   [appkernel.api :as app]

   [apptoolkit.http-server.mod]))



(defn -main []
  (app/start! {:app/name "kunagi"}))
