(ns ^:figwheel-hooks kunagi.browserapp
  (:require
   [appkernel.api :as app]
   [apptoolkit.browserapp.api :as apptoolkit]
   [material-desktop.desktop :as desktop]))


(defn ^:after-load on-figwheel-after-load []
  "Forwards the figwheel reload hook to `apptoolkit`."
  (apptoolkit/on-figwheel-after-load))


(defn KunagiWorkarea
  []
  [:div "kunagi here"])


(app/def-event-handler ::configure-desktop
  :event :appkernel/initialized
  :f (fn [db event]
       (-> db
           (assoc-in [::desktop/desktop :appbar :title] "Some Product Backlog")
           (assoc-in [::desktop/desktop :workarea :components :kunagi] [[KunagiWorkarea]]))))
