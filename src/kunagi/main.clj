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
   [kunagi-base-server.modules.http-server.model]
   [kunagi-base-server.modules.browserapp.model]

   [kunagi.estimating-server :as estimating-server]))


(def-module
  {:module/id ::kunagi})


(def-event
  {:event/id ::client-event
   :event/ident :kunagi/client-event
   :event/module [:module/ident :kunagi]
   :event/req-perms []})


(def-event-handler
  {:event-handler/id ::client-event-handler
   :event-handler/module [:module/ident :kunagi]
   :event-handler/event-ident :kunagi/client-event
   :event-handler/f #(estimating-server/on-client-event %1 %2)})


(appconfig/set-default-config!
 {:http-server/oauth {:google {:enabled? true}}
  :browserapp/lang "en"})


(defn -main []
  (startup/start!
   {:app/info {:app-name "kunagi"
               :app-label "Kunagi"}}))
