(ns kunagi.pbl.projector
  (:require
   [appkernel.api :as app]
   [facts-db.api :as db]))

;;
(def pbl-id "some-random-pbl-id") ; TODO use real id from projection args


(defn init-projection
  [projection]
  (-> projection
      (assoc :db (-> (db/new-db)
                     (db/++ {:db/id pbl-id
                             :items #{}})))))


(defn on-pbl-item-created
  [db event]
  (tap> [::item-created db event])
  (db/++ db pbl-id :items (:item event)))


(defn on-pbl-item-deleted
  [db event]
  (tap> [::item-deleted db event])
  (db/++- db pbl-id :items (:item event)))


(app/def-projector :kunagi/pbl

  :init-projection-f init-projection

  :event-handlers
  [
   {:event :app/started
    :db-f (fn [db event]
            (-> db
                (db/++ pbl-id :items [{:label "Initial Item 1"
                                       :description "Description for first Product Backlog Item."}
                                      {:label "Initial Item 2"
                                       :description "Description for second Product Backlog Item."}])))}

   {:event :kunagi/pbl-item-created
    :doc "Add event's `:item` to pbl's `:items`."
    :db-f on-pbl-item-created}

   {:event :kunagi/pbl-item-deleted
    :doc "Remove event's `:item` from pbl's `:items`."
    :db-f on-pbl-item-deleted}])
