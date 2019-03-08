(ns kunagi.subs
  (:require
   [re-frame.core :as rf]

   [facts-db.api :as db]
   [material-desktop.api :refer [dispatch>]]))


(defn assoc-handlers-to-pbl-item
  [pbl-item]
  (let [id (get pbl-item :db/id)]
    (-> pbl-item
        (assoc-in [:on :discard]
                  ;; #(js/window.alert "click")))))
                  #(rf/dispatch [:kunagi/product-backlog-item-discarded {:id id}])))))


(rf/reg-sub
 :kunagi/product-backlog
 (fn [db _]
   (let [product-backlog (get db :kunagi/dummy-product-backlog)]
     (-> product-backlog
         (db/tree "id-product-backlog-1"
                  {:items {}})
         (assoc-in [:on :add-pbl-item]
                   #(dispatch> [:kunagi/product-backlog-item-added
                                {:id (db/new-uuid)}]))
         (update :items #(mapv assoc-handlers-to-pbl-item %))))))
