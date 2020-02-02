(ns kunagi.kunagi)


(defn new-kunagi []
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
              :participant-id "p-2"}]
   :connected-devices-ids #{"d-1" "d-2"}})


(defn participants-statuses [state]
  (map (fn [participant]
         (-> participant))
       (get state :participants)))


(defn device-connected [state device-id]
  (let [device (or (get-in state [:devices device-id])
                   {:id device-id})]
    (-> state
       (assoc-in [:devices device-id] device)
       (update :connected-devices conj device-id))))
