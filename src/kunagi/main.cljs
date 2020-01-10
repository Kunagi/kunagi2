(ns kunagi.main
  (:require

   [kunagi-base.logging.tap]
   [kunagi-base.enable-asserts]
   [kunagi-base-browserapp.appconfig.load-as-browserapp]

   [kunagi-base.modules.startup.api :as startup]

   [mui-commons.init :as init]

   [kunagi.modules.kunagi.model]
   [kunagi.components.desktop :refer [Desktop]]))


(def VERSION 1)



(defn mount-app []
  (init/mount-app Desktop))


(defn init []
  (init/install-roboto-css)
  (startup/start!
   {:app/info {:app-name "kunagi"
               :app-version (str "2." VERSION)
               :app-label "Kunagi"}})
  (mount-app))


(defn shadow-after-load []
  (mount-app))
