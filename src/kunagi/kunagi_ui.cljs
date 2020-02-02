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


(rf/reg-sub
 ::state
 (fn [db]
   (get db ::state)))



(rf/reg-event-db
 ::init
 (fn [db _]
   (-> db
       (assoc ::state (kunagi/new-state))
       (assoc ::estimating-ui/state (estimating/new-state)))))
