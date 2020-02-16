(ns kunagi.server-impl
  (:require
   [kunagi-base-server.modules.http-server.api :as http-server]
   [kunagi.kunagi :as kunagi]
   [kunagi.estimating :as estimating]))


(defonce !state (atom {:kunagi (kunagi/new-state)
                       :rooms {}
                       :estimating-state (estimating/new-state)}))


(defn broadcast [event]
  (let [send-fn (-> @http-server/!sente-socket :send-fn)
            connected-uids (-> @http-server/!sente-socket :connected-uids deref :any)
            broadcast-f (fn [event]
                          (doseq [uid connected-uids]
                            (send-fn uid [:kunagi-base/event event])))]
    (broadcast-f event)))


(defn handle-kunagi-event [event context]
  (tap> [:!!! ::on-client-event event])
  (let [estimating-state (-> @!state :estimating-state)
        estimating-state (case (-> event :event-name)
                           :kunagi/device-connected estimating-state
                           (estimating/apply-event estimating-state event))]
    (swap! !state assoc :estimating-state estimating-state)
    (broadcast [:kunagi/estimating-state-received estimating-state])))


(defn on-client-event [[event-ident event] context]
  (handle-kunagi-event event context)
  context)
