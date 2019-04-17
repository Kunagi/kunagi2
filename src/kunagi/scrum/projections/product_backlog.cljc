(ns kunagi.scrum.projections.product-backlog
  (:require
   [bindscript.api :refer [def-bindscript]]
   [facts-db.api :as db]
   [facts-db.ddapi :as ddapi :refer [def-event def-query def-api events> <query new-db]]
   [conform.api :as conform]))


(def-api ::scrum.projection.product-backlog
  :db-constructor
  (fn [{:keys [id]}]
    [{:db/id "root"
      :product-backlog id}
     {:db/id id
      :items #{"id-item-1" "id-item-2"}
      :order ["id-item-1" "id-item-2"]}
     {:db/id "id-item-1"
      :label "First Initial Dummy Item"
      :description "This is the description of the first initial dummy item of the dummy product backlog."}
     {:db/id "id-item-2"
      :label "Second Initial Dummy Item"}]))


(def-event ::product-backlog-item-added
  (fn [db {:keys [id label]}]
    (let [product-backlog-id (db/fact db "root" :product-backlog)
          order (db/fact db product-backlog-id :order)
          label (or label "New Product Backlog Item")]
      [
       {:db/id             id
        :label             label}

       {:db/id             product-backlog-id
        [:db/add-1 :items] id
        :order             (conj order id)}])))


(def-event ::product-backlog-item-discarded
  (fn [db {:keys [id]}]
    (let [product-backlog-id (db/fact db "root" :product-backlog)
          order (db/fact db product-backlog-id :order)]
      [
       {:db/id id
        :db/delete true}

       {:db/id product-backlog-id
        [:db/rem-1 :items] id
        :order             (into [] (filter #(not (= % id)) order))}])))


(def-event ::product-backlog-item-reordered
  (fn [db {:keys [id before-id]}]
     (let [product-backlog-id (db/fact db "root" :product-backlog)
           order (db/fact db product-backlog-id :order)
           i1 (.indexOf order id)
           i2 (.indexOf order before-id)]
       [
        {:db/id product-backlog-id
         :order (assoc order i1 (order i2) i2 (order i1))}])))

(def-event ::entity-facts-updated
  (fn [db {:keys [id facts]}]
    ;; TODO store only facts for known entities
    [(merge facts
            {:db/id id})]))
