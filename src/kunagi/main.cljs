(ns kunagi.main
  (:require
   [appkernel.api :as app]
   [apptoolkit.browserapp.api :as browserapp]
   [material-desktop.desktop :as desktop]

   [kunagi.events]
   [kunagi.commands]
   [kunagi.pbl.projector]
   [kunagi.pbl.ui :as pbl-ui]))

(tap> ::loading)

(def start browserapp/start)
