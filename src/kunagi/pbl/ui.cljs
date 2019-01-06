(ns kunagi.pbl.ui
  (:require
   [re-frame.core :as rf]
   ["@material-ui/icons" :as icons]

   [appkernel.api :refer [def-event-handler]]
   [material-desktop.desktop :as desktop]
   [material-desktop.components :as mdc]))


(defn ProductBacklogItemHead [item]
  [mdc/CardContent
   [:> icons/ExpandMore
    {:style {:float :right}}]
   [mdc/Text
    (str "#" (:num item) " " (:label item))]])


(defn ProductBacklogItemContent [item]
  [:div "hidden item content"])


(defn ProductBacklogItem [item]
  [:div.ProductBacklogItem
   [mdc/Card
    [ProductBacklogItemHead item]]])


(defn ProductBacklog []
  [:div#ProductBacklog
   [:div
    {:style {:margin-bottom "1rem"}}
    [mdc/Button {:variant :contained}
                [:> icons/Add]
                "Product Backlog Item"]]

   ;; [:pre (str (:items @(rf/subscribe [::product-backlog])))]
   [mdc/CardsColumn
    {:cards (into (map (fn [item] [ProductBacklogItem item])
                       (:items @(rf/subscribe [::product-backlog]))))}]])

(defn Workarea []
  [ProductBacklog])

(defn install-page [db]
  (-> db
      (assoc-in [::desktop/desktop :appbar :title] "Some Product Backlog")
      (assoc-in [::desktop/desktop :workarea :components :kunagi] [[Workarea]])))


(def-event-handler :page
  :event :kunagi/product-backlog-page-opened
  :f (fn [db event] (install-page db)))


;;; subscriptions

(rf/reg-sub
 ::product-backlog
 (fn [db _]
   {:items (mapv (fn [num]
                   {:num num
                    :label (str "Some random item " num)})
                 (range 10))}))
