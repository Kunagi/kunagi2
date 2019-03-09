(ns kunagi.browserapp.domain-model-module)

(def events
  [

   ;;; events

   [:event-created
    {:id "id-event-session-started"
     :ident :session-started}]

   [:event-created
    {:id "id-event-product-backlog-item-expanded"
     :ident :product-backlog-item-expanded}]

   [:event-created
    {:id "id-event-product-backlog-item-collapsed"
     :ident :product-backlog-item-collapsed}]

   [:event-created
    {:id "id-event-product-backlog-opened"
     :ident :product-backlog-opened}]

   ;;; projections

   [:projection-created
    {:id "id-projection-session"
     :ident :session
     :singleton? true}]])
