(ns kunagi.server
  (:require
   [compojure.route :as compojure-route]

   [appkernel.integration :as integration]
   [appkernel.api :as app]
   [apptoolkit.mod.http-server :as http-server]))


;; TODO use appkernel lifecycle
(defn start! [options]
  (tap> [::start! options])
  (-> (integration/db)
      (merge options)
      (http-server/start!)))


(defn -main [port]
  (tap> ::main)
  (start! {:http-server/port (Integer/parseInt port)
           :http-server/app-js-path "cljs-out/prod-main.js"}))


(defn -main [& args]
  (start!))


(defn figwheel-handler [request]
  (if (and (= :get (:request-method request))
           (= "/"  (:uri request)))
    (do
      (start! {})
      {:status 302
       :headers {"Location" (str "http://localhost:" http-server/dev-port)}})
    {:status 404
     :headers {"Content-Type" "text/plain"}
     :body "404 - Page not found"}))
