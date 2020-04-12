(ns my-project.state.state
  (:require [reagent.core :as reagent :refer [atom]]))

(defonce match (atom nil))
(defonce app-state (atom {:user nil :text "Hello world"}))



