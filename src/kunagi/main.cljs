(ns kunagi.main
  (:require
   [re-frame.core :as rf]

   [kunagi-base.logging.tap]
   [kunagi-base.enable-asserts]
   [kunagi-base-browserapp.appconfig.load-as-browserapp]

   [kunagi-base.modules.startup.api :as startup]
   [kunagi-base-browserapp.modules.comm-async.model]

   [mui-commons.init :as init]

   [kunagi.ui :refer [Desktop]]

   [kunagi-base.appmodel :refer [def-module]]
   [kunagi-base-browserapp.modules.desktop.model :refer [def-page]]

   [kunagi.estimating-ui :as estimating-ui]
   [kunagi.kunagi-ui :as kunagi-ui]))

(def VERSION 1)


(def-module
  {:module/id ::kunagi})


(def-page
  {:page/id ::index-page
   :page/ident :index
   :page/module [:module/ident :kunagi]
   :page/title-text "Kunagi"
   :page/workarea [kunagi-ui/Workarea]})


(defn mount-app []
  (init/mount-app Desktop))


(defn init []
  (init/install-roboto-css)
  (startup/start!
   {:app/info {:app-name "kunagi"
               :app-version (str "2." VERSION)
               :app-label "Kunagi"}})
  (mount-app)
  (rf/dispatch [:kunagi.kunagi-ui/init]))


(defn shadow-after-load []
  (mount-app))
