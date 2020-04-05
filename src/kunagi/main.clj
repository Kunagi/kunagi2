(ns kunagi.main
  (:require
   [kcu.sapp-init]
   [kcu.sapp :as sapp]

   [kunagi-base.modules.startup.api :as startup]
   [kunagi-base.appmodel :refer [def-module]]
   [kunagi-base.modules.events.model :refer [def-event def-event-handler]]

   ;; load kunagi-base modules
   [kunagi-base.modules.auth.model]
   [kunagi-base-server.modules.http-server.model]
   [kunagi-base-server.modules.auth-server.model]
   [kunagi-base-server.modules.browserapp.model]

   [kunagi.appinfo :refer [appinfo]]
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


(sapp/set-default-config
 {:http-server/oauth {:google {:enabled? true}}
  :browserapp/lang "en"})

(sapp/set-appinfo appinfo)

(defn -main []
  (sapp/start)
  (startup/start!))
