(ns my-project.state.state
  (:require [reagent.core :as reagent :refer [atom]]))
(defonce app-state (atom {:user "Sotiris" :text "Hello world" :urlhelpers {:match nil}}))