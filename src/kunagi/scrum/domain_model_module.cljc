(ns kunagi.scrum.domain-model-module)

(def events
  [

   [:type-created
    {:id "id-type-label"
     :ident :label}]

   ;; [:entity-created
   ;;  {:id "id-entity-kunagi-instance"
   ;;   :ident :kunagi-instance}]

   ;; [:entity-created
   ;;  {:id "id-entity-product"
   ;;   :ident :product
   ;;   :container-id "id-entity-kunagi-instance"}]

   ;; [:entity-created
   ;;  {:id "id-entity-issue"
   ;;   :ident :issue
   ;;   :container-id "id-entity-product"}]

   ;; [:entity-created
   ;;  {:id "id-entity-product-backlog"
   ;;   :ident :product-backlog
   ;;   :container-id "id-entity-product"}]

   ;; [:entity-created
   ;;  {:id "id-entity-product-backlog-item"
   ;;   :ident :product-backlog-item
   ;;   :container-id "id-entity-product-backlog"}]

   ;; [:event-created
   ;;  {:id "id-event-user-session-started"
   ;;   :ident :user-session-started}]

   ;; [:projection-created
   ;;  {:id "id-projection-user-session"
   ;;   :ident :user-session}]

   ;; [:event-created
   ;;  {:id "id-event-product-backlog-item-expanded"
   ;;   :ident :product-backlog-item-expanded}]

   [:event-created
    {:id "id-event-product-backlog-created"
     :ident :product-backlog-created}]

   ;; [:projection-created
   ;;  {:id "id-projection-kunagi"
   ;;   :ident :kunagi
   ;;   :sigleton? true}]

   [:event-created
    {:id "id-event-product-backlog-item-added"
     :ident :product-backlog-item-added}]

   [:event-attribute-created
    {:event-id "id-event-product-backlog-item-added"
     :ident :product-backlog
     :type :domain-model.type/entity-reference
     :entity "id-entity-product-backlog"}]

   [:event-attribute-created
    {:event-id "id-event-product-backlog-item-added"
     :ident :label
     :type :domain-model.type/text}]

   [:event-created
    {:id "id-event-product-backlog-item-discarded"
     :ident :product-backlog-item-discarded}]

   [:projection-created
    {:id "id-projection-product-backlog"
     :ident :product-backlog}]

   [:projection-event-handler-created
    {:projection-id "id-projection-product-backlog"
     :event-id "id-event-product-backlog-item-added"}]

   [:projection-event-handler-created
    {:projection-id "id-projection-product-backlog"
     :event-id "id-event-product-backlog-item-discarded"}]

   [:type-created
    {:id "id-type-product-backlog-item-estimation"
     :ident :product-backlog-item-estimation}]])
