(ns kunagi.modules.kunagi.model
  (:require

   [kunagi-base-browserapp.modules.comm-async.model]
   [kunagi-base.modules.auth.model]
   [kunagi-base-browserapp.modules.devtools.model]

   [kunagi-base.appmodel :refer [def-module]]
   [kunagi-base-browserapp.modules.desktop.model :refer [def-page]]
   [kunagi-base-browserapp.modules.assets.model :refer [def-asset-pool]]

   [kunagi.modules.estimate :as estimate]))

(defn Workarea []
  [:div "workarea"])


(def-module
  {:module/id ::kunagi})


(def-page
  {:page/id ::index-page
   :page/ident :index
   :page/module [:module/ident :kunagi]
   :page/title-text "Kunagi"
   :page/workarea [estimate/Workarea]})
