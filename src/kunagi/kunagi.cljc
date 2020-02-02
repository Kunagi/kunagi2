(ns kunagi.kunagi)


(defn new-kunagi []
  {:rooms [{:id "r-1"
            :type :estimating}
           {:id "r-2"
            :type :estimating}]
   :participants [{:id "p-1"
                   :name "Alice"}
                  {:id "p-2"
                   :name "Bob"}]})
