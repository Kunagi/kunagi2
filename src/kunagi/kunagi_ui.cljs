(ns kunagi.kunagi-ui
  (:require
   [reagent.core :as r]
   [re-frame.core :as rf]
   ["@material-ui/core" :as mui]
   ["@material-ui/icons" :as icons]

   [mui-commons.components :as muic]
   [kunagi.kunagi :as kunagi]
   [kunagi.estimating :as estimating]
   [kunagi.estimating-ui :as estimating-ui]))


(defn Debug []
  [:div
   [:hr]
   [estimating-ui/Debug]
   [:h4 "kunagi state"]
   [muic/Data @(rf/subscribe [::state])]])

(defn Workarea-i []
  [:div
   [estimating-ui/Scratchpad]
   [Debug]])

(defn Workarea []
  [Workarea-i])


;;; re-frame


(rf/reg-fx
 :send-event-to-server
 (fn [event]
   (js/console.log "FX" :send-event-to-server event)
   (rf/dispatch [:comm-async/send [:kunagi-base/event [:kunagi/client-event event]]])))


(rf/reg-sub
 ::state
 (fn [db]
   (get db ::state)))



(rf/reg-event-fx
 ::init
 (fn [context _]
   (let [db (get context :db)
         device-id (random-uuid)
         kunagi-state (kunagi/new-state)
         db (-> db
                (assoc ::device-id device-id)
                (assoc ::state kunagi-state))
         event {:event-name :kunagi/device-connected
                :device-id device-id}]
     {:db db
      :send-event-to-server event})))


