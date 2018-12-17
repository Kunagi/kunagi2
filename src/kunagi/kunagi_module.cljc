(ns kunagi.kunagi-module
  (:require
   [domain-model.api :as dm]))


(dm/def-module ::kunagi
  :doc "Kunagi's main application module")


;; (dm/def-entity ::product-backlog)


;; (dm/def-entity ::product-backlog-item)


;; (dm/def-fact ::product-backlog-item.label)


;; (dm/def-component-relation ::product-backlog->product-backlog-item
;;   :arity     :many)


;; (dm/def-view ::product-backlog
;;   :params {:product-backlog [:reference :product-backlog]}
;;   :data-pull [:product-backlog {:items {}}])
