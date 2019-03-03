(ns kunagi.desktop
  (:require

   [material-desktop.components :as mdc]
   [material-desktop.desktop.components.desktop :as desktop]

   [domain-model-editor.components.desktop :as dme-editor]

   [kunagi.pbl.ui :refer [ProductBacklog]]))


(defn create-page [title workarea-component]
  {:appbar {:title [mdc/Double-DIV title "Kunagi"]}
   :workarea {:components [[workarea-component]]}})


(def pages
  {:kunagi/pbl  (create-page "Product Backlog" ProductBacklog)})


(defn Desktop []
  (desktop/PagedDesktop
   {:pages pages
    :home-page :kunagi/pbl}))
