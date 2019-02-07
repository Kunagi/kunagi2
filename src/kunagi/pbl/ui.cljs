(ns kunagi.pbl.ui
  (:require
   [re-frame.core :as rf]
   ["@material-ui/icons" :as icons]

   [appkernel.api :as app]
   [facts-db.api :as db]
   [material-desktop.desktop :as desktop]
   [material-desktop.components :as mdc]
   [material-desktop.expansion-panel-list :as expansion-panel-list]))



(defn ProductBacklog []
  (let [pbl @(rf/subscribe [::pbl])]
    [:div#ProductBacklog
     [:pre (str "pbl: " pbl)]
     ;; [:pre (str "sub: " @(rf/subscribe [:app/projection-db {:name :kunagi/pbl :args {}}]))]
     [:div
      {:style {:margin-bottom "1rem"}}
      [mdc/Button {:variant :contained
                   :on-click (:add-item-on-click pbl)}
                  ;;[:> icons/Add]
       "Add Product Backlog Item"]]
     [expansion-panel-list/ExpansionPanelList
      {:panels (map (fn [pbl-item]
                      {:summary {:text (:label pbl-item)}})
                    (:items pbl))}]]))


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
 ::pbl
 (fn [_]
   (rf/subscribe [:app/projection-db {:name :kunagi/pbl}]))
 (fn [projection-db _]
   (let [pbl-id "some-random-pbl-id"]
     (-> (db/tree projection-db pbl-id {:items {}})
         (assoc :add-item-on-click
                #(rf/dispatch [:kunagi/pbl-item-created
                               {:pbl pbl-id
                                :item {:db/id (str (random-uuid))
                                       :label (str "New item " (.getTime (js/Date.)))}}]))))))
