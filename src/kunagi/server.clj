(ns kunagi.server
  (:require
   [apptoolkit.mod.http-server :as http-server]))



(defn start! []
  (prn "START!")
  ;; (app/start!))
  (http-server/start! {}))


(defn -main [& args]
  (start!))


(defn figwheel-handler [request]
  (if (and (= :get (:request-method request))
           (= "/"  (:uri request)))
    (do
      (start!)
      {:status 302
       :headers {"Location" (str "http://localhost:" http-server/port)}})
    {:status 404
     :headers {"Content-Type" "text/plain"}
     :body "404 - Page not found"}))
