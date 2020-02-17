(ns kunagi.main
  (:require

   [kunagi-base.logging.tap-formated]
   [kunagi-base.enable-asserts]
   [kunagi-base.appconfig.load-as-server]
   [kunagi-base.appconfig.api :as appconfig]

   [kunagi-base.modules.startup.api :as startup]
   [kunagi-base.appmodel :refer [def-module]]
   [kunagi-base.modules.events.model :refer [def-event def-event-handler]]

   ;; load kunagi-base modules
   [kunagi-base.modules.auth.model]
   [kunagi-base-server.modules.http-server.model]
   [kunagi-base-server.modules.auth-server.model]
   [kunagi-base-server.modules.browserapp.model]

   [kunagi.server-impl :as impl]))


(def-module
  {:module/id ::kunagi})


(def-event
  {:event/id ::client-event
   :event/req-perms []})


(def-event-handler
  {:event-handler/id ::client-event-handler
   :event-handler/event-ident :kunagi/client-event
   :event-handler/f #(impl/on-client-event %1 %2)})


(appconfig/set-default-config!
 {:http-server/oauth {:google {:enabled? true}}
  :browserapp/lang "en"})


(defn -main []
  (startup/start!
   {:app/info {:app-name "kunagi"
               :app-label "Kunagi"}}))
