(ns kunagi.events
  (:require
   [appkernel.api :as app]))


(app/def-event :kunagi/pbl-item-created)

(app/def-event :kunagi/pbl-item-deleted)
