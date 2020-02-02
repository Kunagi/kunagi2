(ns kunagi.kunagi)


(defn new-state []
  {:rooms [{:id "r-1"
            :type :estimating}
           {:id "r-2"
            :type :estimating}]
   :participants [{:id "p-1"
                   :name "Alice"}
                  {:id "p-2"
                   :name "Bob"}]
   :devices [{:id "d-1"
              :participant-id "p-1"}
             {:id "d-2"
              :participant-id "p-2"}
             {:id "d-3"
              :participant-id "p-2"}]
   :connected-devices-ids #{"d-1" "d-2"}})


;; TODO: write body
(defn connected-participants-statuses [state]
  (map (fn [participant]
         (-> participant))
       (get state :participants)))


(defn on-device-connected [state device-id]
  (let [device (or (get-in state [:devices device-id])
                   {:id device-id})]
    (-> state
       (assoc-in [:devices device-id] device)
       (update :connected-devices conj device-id))))


(defn apply-event [state [event-name & args]]
  (case event-name
    :device-connected (on-device-connected state args)
    (throw (ex-info (str "Unsupported event: " event-name)
                    {:event-name event-name
                     :event-args args}))))
