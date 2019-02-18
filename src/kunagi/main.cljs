(ns kunagi.main
  (:require
   [apptoolkit.browserapp.api :as browserapp]

   [apptoolkit.devtools.headsup.mod]

   [kunagi.events]
   [kunagi.commands]
   [kunagi.pbl.projector]
   [kunagi.pbl.ui :as pbl-ui]
   [kunagi.desktop :as desktop]))


(defn Root []
  [desktop/Desktop])


(defn ^:export start [config-edn]
  (browserapp/start config-edn [Root]))
