(ns kunagi.pbl.ui
  (:require
   [re-frame.core :as rf]
   ["@material-ui/icons" :as icons]

   [appkernel.api :as app]
   [facts-db.api :as db]
   [material-desktop.desktop :as desktop]
   [material-desktop.components :as mdc]))


(defn ProductBacklogItemHead [item]
  [mdc/CardContent
   [:> icons/ExpandMore
    {:style {:float :right}}]
   [mdc/Text
    (:label item)]])


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
    [mdc/Button {:variant :contained
                 :on-click #(rf/dispatch [:kunagi/pbl-item-created {:pbl "some-random-pbl-id"
                                                                    :item {:db/id (str (random-uuid))
                                                                           :label "New item"}}])}
                ;;[:> icons/Add]
                "Add Product Backlog Item"]]

   [:pre (str "pbl: " @(rf/subscribe [::product-backlog]))]
   [:pre (str "sub: " @(rf/subscribe [:app/projection-db {:name :kunagi/pbl :args {}}]))]
   [mdc/CardsColumn
    {:cards (into (map (fn [item] [ProductBacklogItem item])
                       (:items @(rf/subscribe [::product-backlog]))))}]])

(defn Workarea []
  [ProductBacklog])

(defn install-page [db]
  (-> db
      (assoc-in [::desktop/desktop :appbar :title] "Some Product Backlog")
      (assoc-in [::desktop/desktop :workarea :components :kunagi] [[Workarea]])))



(app/def-event-handler ::install-page
  :event :app/started
  :f (fn [db event]
       (install-page db)))


;;; subscriptions

(rf/reg-sub
 ::product-backlog
 (fn [_ _]
   (rf/subscribe [:app/projection-db {:name :kunagi/pbl}]))
 (fn [projection-db _]
   (db/tree projection-db "some-random-pbl-id" {:items {}})))
