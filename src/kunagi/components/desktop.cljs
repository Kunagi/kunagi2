(ns kunagi.components.desktop
  (:require
   ["@material-ui/core" :as mui]

   [material-desktop.api :refer [dispatch>]]
   [material-desktop.components :as mdc]
   [material-desktop.desktop.components.desktop :as desktop]

   [apptoolkit.domain-model-editor.components.desktop :as dme-editor]

   [kunagi.components.product-backlog-page :refer [ProductBacklogWorkarea]]))


(defn PblButton []
  [:> mui/Button
   {:style {:color :inherit}
    :on-click #(dispatch> [:material-desktop/desktop.page-switch-requested
                           {:page-key :app/home}])}
   "Product Backlog"])


(defn DmButton []
  [:> mui/Button
   {:style {:color :inherit}
    :on-click #(dispatch> [:material-desktop/desktop.page-switch-requested
                           {:page-key :domain-model-editor/model}])}
   "Domain Model"])

(defn create-page [title workarea-component]
  {:appbar {:title [mdc/Double-DIV title "Kunagi"]}
   :workarea {:components [[workarea-component]]}})


(def pages
  (merge
   dme-editor/pages
   {:app/home  (create-page "Product Backlog" ProductBacklogWorkarea)}))


(defn Desktop []
  (desktop/PagedDesktop
   {:appbar {:toolbar-components [[PblButton] [DmButton]]}
    :pages pages}))
