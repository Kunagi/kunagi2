(ns kunagi.commands
  (:require
   [appkernel.api :as app]))


(def pbl-id "some-random-pbl-id")


(defn create-pbl-item [args]
  [{:app/event :kunagi/pbl-item-created
    :pbl pbl-id
    :item {:db/id (app/new-uuid)
           :label (str "New item " (app/current-time-millis))
           :description "Description for new Product Backlog item. Description for new Product Backlog item. Description for new Product Backlog item. Description for new Product Backlog item. Description for new Product Backlog item. "}}])


(app/def-command :kunagi/create-pbl-item
  :f create-pbl-item)


(defn delete-pbl-item [args]
  [{:app/event :kunagi/pbl-item-deleted
    :item (:item args)}])


(app/def-command :kunagi/delete-pbl-item
  :f delete-pbl-item)
