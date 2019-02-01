(ns ^:figwheel-hooks kunagi.figwheel-adapter
  (:require
   [apptoolkit.figwheel-adapter :as figwheel-adapter]

   [kunagi.main]))


(defn ^:after-load on-figwheel-after-load []
  "Forwards the figwheel reload hook to `apptoolkit`."
  (figwheel-adapter/on-figwheel-after-load))
