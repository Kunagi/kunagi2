(ns kunagi.projections.product-backlog
  (:require
   [bindscript.api :refer [def-bindscript]]
   [facts-db.api :as db]
   [facts-db.ddapi :as ddapi :refer [def-event def-query def-api events> <query new-db]]
   [conform.api :as conform]))


(def-api ::kunagi.projection.product-backlog
  :db-constructor
  (fn [{:keys [id]}]
    [{:db/id "root"
      :product-backlog id}
     {:db/id id
      :items #{"id-item-1" "id-item-2"}}
     {:db/id "id-item-1"
      :label "First Initial Dummy Item"
      :description "This is the description of the first initial dummy item of the dummy product backlog."}
     {:db/id "id-item-2"
      :label "Second Initial Dummy Item"}]))


(def-event ::product-backlog-item-added
  (fn [db {:keys [id label]}]
    (let [product-backlog-id (db/fact db "root" :product-backlog)
          label (or label "New Product Backlog Item")]
      [
       {:db/id             id
        :label             label}

       {:db/id             product-backlog-id
        [:db/add-1 :items] id}])))


(def-event ::product-backlog-item-discarded
  (fn [db {:keys [id]}]
    (let [product-backlog-id (db/fact db "root" :product-backlog)]
      [
       {:db/id id
        :db/delete true}

       {:db/id product-backlog-id
        [:db/rem-1 :items] id}])))


(def-event ::entity-facts-updated
  (fn [db {:keys [id facts]}]
    [(merge facts
            {:db/id id})]))
