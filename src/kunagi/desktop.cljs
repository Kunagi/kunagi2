(ns kunagi.desktop
  (:require

   [appkernel.api :as app]
   [material-desktop.desktop :as desktop]

   [kunagi.pbl.ui :as pbl-ui]))


(defn Workarea []
  [pbl-ui/ProductBacklog])


(defn AppbarToolbar []
  [:div])


(defn Desktop []
  (desktop/Desktop
   :appbar {:title "Kunagi Product Backlog"
            :toolbar-components [[AppbarToolbar]]}
   :workarea {:components [[Workarea]]}))
