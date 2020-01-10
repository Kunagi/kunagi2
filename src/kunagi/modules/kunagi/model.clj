(ns kunagi.modules.kunagi.model
  (:require
   [kunagi-base-server.modules.http-server.model]
   [kunagi-base-server.modules.browserapp.model]

   [kunagi-base.appmodel :refer [def-module]]
   [kunagi-base.modules.startup.model :refer [def-init-function]]))



(def-module
  {:module/id ::kunagi})
