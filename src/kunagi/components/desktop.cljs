(ns kunagi.components.desktop
  (:require
   ["@material-ui/core" :as mui]

   [kunagi-base-browserapp.modules.desktop.components :as desktop]
   [kunagi-base-browserapp.components :as kbc]))


(defn devutils? [] true)


(defn HomeIcon []
  [:a
   {:href "/ui/"}
   [:img
    {:src "/img/frankenburg-orga-icon_192.png"
     :width "32px"
     :alt "Kunagi"}]])


(defn AppBar []
  [:> mui/AppBar
   [:> mui/Toolbar

    [desktop/MainNavIconButtonSwitch
     [HomeIcon]]

    [:div
     {:style {:flex-grow 1}}]

    (into [:div
           {:style {:display :flex}}]
          [[kbc/CommAsyncStatusIndicator]])]])


(defn Desktop []
  [desktop/Desktop
   {:app-bar [AppBar]}])
