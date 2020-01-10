(ns kunagi.main
  (:require

   [kunagi-base.logging.tap-formated]
   [kunagi-base.enable-asserts]
   [kunagi-base.appconfig.load-as-server]
   [kunagi-base.appconfig.api :as appconfig]

   [kunagi-base.modules.startup.api :as startup]

   [kunagi.modules.kunagi.model]))


(appconfig/set-default-config!
 {:http-server/oauth {:google {:enabled? true}}
  :browserapp/lang "en"})


(defn -main []
  (startup/start!
   {:app/info {:app-name "kunagi"
               :app-label "Kunagi"}}))
