(ns ^:figwheel-hooks kunagi.browserapp
  (:require
   [appkernel.api :as app]
   [apptoolkit.browserapp.api :as apptoolkit]
   [material-desktop.desktop :as desktop]

   [kunagi.pbl.ui :as pbl-ui]))


(defn ^:after-load on-figwheel-after-load []
  "Forwards the figwheel reload hook to `apptoolkit`."
  (apptoolkit/on-figwheel-after-load))


(defn KunagiWorkarea
  []
  [:div "kunagi here"])


(app/def-event-handler ::configure-desktop
  :event :appkernel/initialized
  :f (fn [db event]
       (pbl-ui/install-page db)))
