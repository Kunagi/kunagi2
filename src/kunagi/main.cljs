(ns kunagi.main
  (:require
   [re-frame.core :as rf]

   [kcu.butils :as bu]
   [kcu.bapp-init]
   [kcu.bapp :as bapp]


   [kunagi-base.modules.startup.api :as startup]
   [kunagi-base-browserapp.modules.auth]



   [kunagi-base.appmodel :refer [def-module]]
   [kunagi-base-browserapp.modules.desktop.model :refer [def-page]]

   [kunagi.appinfo :refer [appinfo]]
   [kunagi.ui :as ui]))


(def-module
  {:module/id ::kunagi})


(def-page
  {:page/id ::index
   :page/ident :index
   :page/module [:module/ident :kunagi]
   :page/title-text "Kunagi"
   :page/workarea [ui/Workarea]})


(bapp/set-appinfo appinfo)


(defn init []
  (bu/install-roboto-font-link)
  (startup/start!)
  (rf/dispatch [:kunagi.ui/init])
  (bapp/mount-app ui/Desktop))


(defn shadow-after-load []
  (bapp/mount-app ui/Desktop))
