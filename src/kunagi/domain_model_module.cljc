(ns kunagi.domain-model-module)

(def events
  [
   [:entity-created
    {:id "id-entity-product-backlog"
     :ident :product-backlog}]

   [:entity-created
    {:id "id-entity-product-backlog-item"
     :ident :product-backlog-item
     :container-id "id-entity-product-backlog"}]

   [:event-created
    {:id "id-event-product-backlog-item-added"
     :ident :product-backlog-item-added}]

   [:event-created
    {:id "id-event-product-backlog-item-discarded"
     :ident :product-backlog-item-discarded}]

   [:projection-created
    {:id "id-projection-product-backlog"
     :ident :product-backlog}]

   [:type-created
    {:id "id-type-product-backlog-item-estimation"
     :ident :product-backlog-item-estimation}]])
