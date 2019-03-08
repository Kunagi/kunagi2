(ns kunagi.events
  (:require
   [re-frame.core :as rf]

   [facts-db.ddapi :as ddapi]))


(rf/reg-event-db
 :kunagi/init
 (fn [db _]
   (-> db
       (assoc :kunagi/dummy-product-backlog
              (ddapi/new-db :kunagi.projection.product-backlog
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
