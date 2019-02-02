(ns kunagi.events
  (:require
   [appkernel.api :as app]))


(app/def-eventmodel :kunagi/pbl-item-created)
