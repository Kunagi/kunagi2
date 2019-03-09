(ns kunagi.browserapp.projections.session
  (:require
   [bindscript.api :refer [def-bindscript]]
   [facts-db.api :as db]
   [facts-db.ddapi :as ddapi :refer [def-event def-query def-api events> <query new-db]]
   [conform.api :as conform]))


(def-api ::browserapp.projection.session
  :db-constructor
  (fn [{:keys []}]
    [{:db/id "root"
      :current-product-backlog "id-product-backlog-1"
      :expanded-product-backlog-item nil}]))


(def-event ::session-started
  (fn [db {:keys []}]
    db))


(def-event ::product-backlog-item-expanded
  (fn [db {:keys [id]}]
    [{:db/id "root"
      :expanded-product-backlog-item id}]))


(def-event ::product-backlog-item-collapsed
  (fn [db {:keys []}]
    [{:db/id "root"
      :expanded-product-backlog-item nil}]))
