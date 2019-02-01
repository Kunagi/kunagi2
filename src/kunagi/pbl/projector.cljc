(ns kunagi.pbl.projector
  (:require
   [appkernel.api :as app]
   [facts-db.api :as db]))

;; TODO pbl id required here...
(defn init-projection
  [projection]
  (-> projection
      (assoc :db (-> (db/new-db)
                     (db/update-entity {:db/id :pbl})))))


(defn on-pbl-item-created
  [projection event]
  (tap> "!!!!!!!!!!!!!!!!!!")
  (tap> event)
  (tap> projection)
  (assoc projection :event event))


(app/def-projector :kunagi/pbl

  :init-projection-f init-projection

  :event-handlers
  [{:event :kunagi/pbl-item-created
    :f on-pbl-item-created}])
