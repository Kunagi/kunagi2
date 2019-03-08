(ns kunagi.main
  (:require
   [re-frame.core :as rf]

   [apptoolkit.browserapp.api :as browserapp]
   [apptoolkit.devtools.headsup.mod]

   [apptoolkit.domain-model-editor.mod]

   [kunagi.domain-model-module :as domain-model-module]
   [kunagi.events]
   [kunagi.subs]
   [kunagi.projections.product-backlog]
   [kunagi.components.desktop :refer [Desktop]]))


(defn Root []
  [Desktop])


(defn ^:export start [config-edn]
  (browserapp/start
   config-edn
   [Root]
   {:domain-model/modules-events {:kunagi domain-model-module/events}})
  (rf/dispatch-sync [:kunagi/init]))
