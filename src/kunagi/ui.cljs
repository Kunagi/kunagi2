(ns kunagi.ui
  (:require
   ["@material-ui/core" :as mui]

   [kunagi-base-browserapp.modules.desktop.components :as desktop]
   [kunagi-base-browserapp.components :as kbc]))



(defn AppBar []
  [:> mui/AppBar
   [:> mui/Toolbar

    [:div
     "Kunagi Estimating"]

    [:div
     {:style {:flex-grow 1}}]

    (into [:div
           {:style {:display :flex}}]
          [[kbc/CommAsyncStatusIndicator]])]])


(defn Desktop []
  [desktop/Desktop
   {:app-bar [AppBar]}])
