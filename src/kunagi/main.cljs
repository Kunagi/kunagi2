(ns kunagi.main
  (:require
   [re-frame.core :as rf]

   [kunagi-base.logging.tap]
   [kunagi-base.enable-asserts]
   [kunagi-base-browserapp.appconfig.load-as-browserapp]

   [kunagi-base.modules.startup.api :as startup]
   [kunagi-base-browserapp.modules.auth]

   [mui-commons.init :as init]


   [kunagi-base.appmodel :refer [def-module]]
   [kunagi-base-browserapp.modules.desktop.model :refer [def-page]]

   [kunagi.ui :as ui]))


(def VERSION 1)


(def-module
  {:module/id ::kunagi})


(def-page
  {:page/id ::index
   :page/ident :index
   :page/module [:module/ident :kunagi]
   :page/title-text "Kunagi"
   :page/workarea [ui/Workarea]})


(defn mount-app []
  (init/mount-app ui/Desktop))


(defn init []
  (init/install-roboto-css)
  (startup/start!
   {:app/info {:app-name "kunagi"
               :app-version (str "2." VERSION)
               :app-label "Kunagi"}})
  (mount-app)
  (rf/dispatch [:kunagi.ui/init]))


(defn shadow-after-load []
  (mount-app))
