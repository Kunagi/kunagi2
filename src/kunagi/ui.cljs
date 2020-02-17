(ns kunagi.ui
  (:require
   [reagent.core :as r]
   [re-frame.core :as rf]
   ["@material-ui/core" :as mui]
   ["@material-ui/icons" :as icons]

   [mui-commons.api :refer [<subscribe]]
   [mui-commons.components :as muic]
   [kunagi-base-browserapp.modules.desktop.components :as desktop]
   [kunagi-base-browserapp.components :as kbc]

   [kunagi.kunagi :as kunagi]
   [kunagi.estimating-ui :as estimating-ui]))


;;; index page

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



;;; base


(defn- user-name [user]
  (if-let [n (-> user :user/name)]
    n
    (if-let [info (-> user :user/oauth-userinfo)]
      (if-let [n (-> info :given_name)]
        n
        (if-let [n (-> info :email)]
          n
          (-> user (:user/id))))
      (-> user (:user/id)))))


(defn UserMenu [user]
  [muic/DropdownMenu
   {:button-text (user-name user)}
   (fn [callback]
     [
      [:> mui/MenuItem
       {:on-click #(do
                     (callback)
                     (set! (.-location js/window) "/sign-out"))}
       "Logout"]])])


(defn LoginButton []
  [:> mui/Button
   {:color :inherit
    :on-click #(set! (.-location js/window) "/oauth/google")}
   "Login"])


(defn UserStatusForAppBar []
  (if-let [user (<subscribe [:auth/user])]
    [UserMenu user]
    [LoginButton]))

(defn AppBar []
  [:> mui/AppBar
   [:> mui/Toolbar

    [:div
     "Kunagi Estimating"]

    [:div
     {:style {:flex-grow 1}}]

    (into [:div
           {:style {:display :flex}}]
          [[kbc/CommAsyncStatusIndicator]
           [UserStatusForAppBar]])]])

(defn Desktop []
  [desktop/Desktop
   {:app-bar [AppBar]}])



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
