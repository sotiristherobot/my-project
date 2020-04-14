(ns my-project.pages.login
  (:require
   [my-project.state.state :as state :refer [app-state]]))

(defn on-button-click []
  "On login button click fake login by adding a user so we can login in"
  (swap! app-state assoc :user "Sotiris")
  (println @app-state))

(defn login-page []
  [:div
   [:h1 "This is the login page"]
   [:input {:type "button" :value "Fake login" :on-click on-button-click}]])