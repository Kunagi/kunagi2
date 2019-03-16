(ns kunagi.events
  (:require
   [re-frame.core :as rf]

   [facts-db.api :as db]
   [facts-db.ddapi :as ddapi]
   [material-desktop.desktop.api :as desktop]
   [material-desktop.desktop.navigation :as navigation]))


(rf/reg-event-db
 :kunagi/init
 (fn [db _]
   (-> db
       (assoc :kunagi/dummy-product-backlog
              (ddapi/new-db :scrum.projection.product-backlog
                            {:id "id-product-backlog-1"})))))


(rf/reg-event-db
 :kunagi/product-backlog-item-added
 (fn [db [_ {:keys [id]}]]
   (-> db
       (update :kunagi/dummy-product-backlog
               ddapi/events>
               [[:product-backlog-item-added
                 {:id id}]]))))


(rf/reg-event-db
 :kunagi/product-backlog-item-discarded
 (fn [db [_ {:keys [id]}]]
   (-> db
       (update :kunagi/dummy-product-backlog
               ddapi/events>
               [[:product-backlog-item-discarded
                 {:id id}]]))))

(rf/reg-event-db
 :kunagi/product-backlog-item-reordered
 (fn [db [_ {:keys [id before-id]}]]
    (-> db
        (update :kunagi/dummy-product-Backlog
                ddapi/events>
                [[:product-backlog-item-reordered
                  {:id id :before-id id}]]))))


(rf/reg-event-db
 :kunagi/edit-entity-fact-triggered
 (fn [db [_ {:keys [entity-id fact current-value]}]]
   (let [a :b]
     (-> db
         (desktop/open-form-dialog
          :title "Edit fact"
          :form {:fields [{:key fact
                           :label (str fact)
                           :default-value current-value
                           :auto-focus true}]}
          :submit-event [:kunagi/edit-entity-facts-submitted
                         {:entity-id entity-id}])))))


(rf/reg-event-db
 :kunagi/edit-entity-facts-submitted
 (fn [db [_ {:keys [values entity-id]}]]
   (-> db
       (update-in [:kunagi/dummy-product-backlog]
                  ddapi/events> [[:entity-facts-updated
                                  {:id entity-id
                                   :facts values}]]))))
