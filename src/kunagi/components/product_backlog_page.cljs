(ns kunagi.components.product-backlog-page
  (:require
   [re-frame.core :as rf]
   ["@material-ui/icons" :as icons]

   [appkernel.api :as app]
   [facts-db.api :as db]
   [material-desktop.api :refer [dispatch>]]
   [material-desktop.components :as mdc]
   [material-desktop.fieldset :as fieldset]
   [material-desktop.expansion-panel-list :as expansion-panel-list]))


(defn ProductBacklogItemDetails [pbl-item-panel-model]
  (let [pbl-item (get pbl-item-panel-model :pbl-item)]
    [:div
     {:style {:display :flex
              :width "100%"}}
     [:div
      {:style {:flex-grow 1}}
      [fieldset/Fieldset
       :rows [{:fields [{:label "Label"
                         :value (get pbl-item :label)
                         :on-click #(dispatch> [:kunagi/edit-entity-fact-triggered
                                                {:entity-id (:db/id pbl-item)
                                                 :fact :label
                                                 :current-value (get pbl-item :label)}])}
                        {:label "Estimation"
                         :value "3 Story Points"}]}
              {:fields [{:label "Description"
                         :value (get pbl-item :description)
                         :on-click #(dispatch> [:kunagi/edit-entity-fact-triggered
                                                {:entity-id (:db/id pbl-item)
                                                 :fact :description
                                                 :current-value (get pbl-item :description)}])}]}
              {:fields [{:label "Requirements"}]}]]]

     [:div
      {:style {:margin-left "2rem"}}
      [mdc/ButtonsColumn
       :title "Actions"
       :buttons [{:text "discard"
                  :on-click (-> pbl-item :on :discard)}]]]]))



(defn ProductBacklogWorkarea []
  (let [pbl @(rf/subscribe [:kunagi/product-backlog])]
    [:div#ProductBacklog
     ;; [mdc/Data pbl]
     ;; [:hr]
     [:div
      {:style {:margin-bottom "1rem"}}
      [mdc/Button
       :on-click (-> pbl :on :add-pbl-item)
       :text "Add Product Backlog Item"]]
     [expansion-panel-list/ExpansionPanelList
      :panels (map (fn [pbl-item]
                     {:summary {:text (:label pbl-item)}
                      :details {:component ProductBacklogItemDetails
                                :pbl-item pbl-item}})
                   (sort-by #(.indexOf (:order pbl) (:db/id %)) (:items pbl)))]]))
