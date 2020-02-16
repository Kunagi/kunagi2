(ns kunagi.estimating-server
  (:require
   [kunagi-base-server.modules.http-server.api :as http-server]
   [kunagi.estimating :as estimating]))


(defonce !state (atom {:estimating-state (estimating/new-state)}))



(defn on-client-event [[event-ident event] context]
  (tap> [:!!! ::on-client-event event])
  (let [response-f (-> context :comm/response-f)
        send-fn (-> @http-server/!sente-socket :send-fn)
        connected-uids (-> @http-server/!sente-socket :connected-uids deref :any)
        broadcast-f (fn [event]
                      (doseq [uid connected-uids]
                        (tap> [:!!! ::respond-to uid])
                        (send-fn uid event)))
        estimating-state (-> @!state :estimating-state)
        estimating-state (case (-> event :event-name)
                           :kunagi/device-connected estimating-state
                           (estimating/apply-event estimating-state event))]
    (swap! !state assoc :estimating-state estimating-state)
    (broadcast-f [:kunagi/estimating-state-received estimating-state]) ;; FIXME
    (response-f [:kunagi/estimating-state-received estimating-state]))
  context)
