(ns kunagi.pbl.ui
  (:require
   [re-frame.core :as rf]
   ["@material-ui/icons" :as icons]

   [appkernel.api :as app]
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
    [mdc/Button {:variant :contained
                 :on-click (fn [src]
                             (tap> "click!")
                             (rf/dispatch [:kunagi/pbl-item-created]))}
                ;;[:> icons/Add]
                "Add Product Backlog Item"]]

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



(app/def-event-handler ::install-page
  :event :app/started
  :f (fn [db event]
       (install-page db)))


;;; subscriptions

(rf/reg-sub
 ::product-backlog
 (fn [db _]
   {:items (mapv (fn [num]
                   {:num num
                    :label (str "Some random item " num)})
                 (range 10))}))
