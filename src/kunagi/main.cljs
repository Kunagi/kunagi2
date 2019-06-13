(ns kunagi.main
  (:require
   [re-frame.core :as rf]

   [apptoolkit.browserapp.api :as browserapp]
   [apptoolkit.devtools.headsup.mod]

   [apptoolkit.domain-model-editor.mod]

   [material-desktop.desktop.domain-model-module :as desktop-domain]
   [kunagi.scrum.domain-model-module :as scrum-domain]
   [kunagi.browserapp.domain-model-module :as browserapp-domain]
   [kunagi.events]
   [kunagi.subs]
   [kunagi.scrum.projections.product-backlog]
   [kunagi.components.desktop :refer [Desktop]]))


(defn Root []
  [Desktop])


(defn ^:export start [config-edn]
  (browserapp/start
   config-edn
   [Root]
   {:domain-model/modules-events {:scrum scrum-domain/events
                                  :desktop desktop-domain/events}})
                                  ;;:browserapp browserapp-domain/events}})
  (rf/dispatch-sync [:kunagi/init]))
