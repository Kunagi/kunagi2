(ns kunagi.estimating-ui
  (:require
   [reagent.core :as r]
   [re-frame.core :as rf]
   ["@material-ui/core" :as mui]
   ["@material-ui/icons" :as icons]

   [mui-commons.components :as muic]
   [kunagi.estimating :as estimating]))


(defn PublicScreen [state]
  [:div
   [:h4 "public screen x"]
   (when-let [item (-> state estimating/current-item)]
     [muic/Stack
      [muic/Card
       (get item :text)]
      (into
       [muic/Inline]
       (map (fn [participant]
              (let [value (estimating/participant-estimation-value state (-> participant :id))
                    revealed? (-> state :estimations-revealed)]
                [muic/Card
                 [muic/Stack
                  {:style {:text-align :center}}
                  (-> participant :name)
                  [:> mui/Avatar
                   {:style {:background-color (when value :green)}}
                   (-> participant :name first)]
                  (when revealed?
                    [:h1 value])]]))
            (-> state :participants)))])])


(defn ModeratorScreen [state]
  [:div
   [:h4 "moderator screen"]
   [:> mui/Button
    {:on-click #(rf/dispatch [:kunagi.kunagi-ui/init])}
    "Init"]
   (when-let [item (-> state estimating/current-item)]
     [:div
      [:> mui/Button
       {:on-click #(rf/dispatch [::estimation-started])}
       "Begin"]
      [:> mui/Button
       {:on-click #(rf/dispatch [::estimations-revealed])}
       "Reveal"]])])


(defn ParticipantScreen [state participant-id]
  [:div
   [:h4 (-> state (estimating/participant participant-id) :name)]
   (when (-> state :estimation-started)
     [muic/Card
      [:> mui/Select
       {:value (estimating/participant-estimation-value state participant-id)
        :on-change #(rf/dispatch [::participant-selected-estimation participant-id (-> % .-target .-value)])}
       [:> mui/MenuItem
        {:value "1"}
        "1"]
       [:> mui/MenuItem
        {:value "2"}
        "2"]
       [:> mui/MenuItem
        {:value "99"}
        "99"]]])])



(defn Scratchpad []
  (let [state @(rf/subscribe [::state])]
    [:div
     [PublicScreen state]
     [:hr]
     [:div
      {:style {:display :grid
               :grid-template-columns "1fr 1fr"
               :grid-gap "1em"}}
      [ParticipantScreen state "p-1"]
      [ParticipantScreen state "p-2"]]
     [:hr]
     [ModeratorScreen state]]))

(defn Debug []
  [:div
   [:h4 "estimating state"]
   [muic/Data @(rf/subscribe [::state])]])


(rf/reg-sub
 ::state
 (fn [db]
   (get db ::state)))


(rf/reg-event-fx
 :participant-selected-estimation
 (fn [context [_ participant-id value]]
   (let [db (get context :db)
         event {:event-name :estimating/participant-selected-estimation
                :room-id nil ;TODO
                :participant-id participant-id
                :value value}
         db (update db ::state estimating/apply-event event)]
     {:db db
      :send-event-to-server event})))


(rf/reg-event-db
 ::estimation-started
 (fn [db _]
   (update db ::state estimating/estimation-started)))


(rf/reg-event-db
 ::estimations-revealed
 (fn [db _]
   (update db ::state estimating/estimations-revealed)))
