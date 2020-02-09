(ns kunagi.estimating)


(defn new-state []
  {:participants [{:id "p-1"
                   :name "Alice"}
                  {:id "p-2"
                   :name "Bob"}]
   :items [{:id "bi-1"
            :text "Some Item 1"}
           {:id "bi-2"
            :text "Some Item 2"}]
   :current-item-id "bi-1"
   :estimation-started nil
   :estimations-revealed nil
   :estimations-by-participant-id {}})


(defn participant [state participant-id]
  (first
   (filter #(= participant-id (get % :id))
           (-> state :participants))))


(defn current-item [state]
  (let [item-id (-> state :current-item-id)]
    (first
     (filter #(= item-id (get % :id))
             (-> state :items)))))


(defn participant-estimation-value [state participant-id]
  (get-in state [:estimations-by-participant-id participant-id]))


(defn estimation-started [state]
  (assoc state :estimation-started true))


(defn estimations-revealed [state]
  (assoc state :estimations-revealed true))

(defn on-participant-selected-estimation [state participant-id value]
  (assoc-in state [:estimations-by-participant-id participant-id] value))


;; TODO other events
(defn apply-event [state {:keys [event-name] :as event}]
  (case event-name
    :estimating/participant-selected-estimation
    (on-participant-selected-estimation state (get event :participant-id)
                                              (get event :value))
    (throw (ex-info (str "Unsupported event: " event-name)
                    {:event event}))))

(comment
  (def state (new-state))
  (def item-id "bi-2"))
