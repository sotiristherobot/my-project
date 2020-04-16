(ns my-project.pages.login
  (:require
    [my-project.state.state :as state :refer [app-state]]
    [reagent-forms.core :refer [bind-fields]]
    [reagent.core :as r]))

(defn on-button-click []
      "On login button click fake login by adding a user so we can login in"
      (swap! app-state assoc :user "Sotiris")
      (println @app-state))

(def login-form-template
  [:form
   [:div[:label "username: "
        [:input.form-control {:field :text :id :username}]]]
   [:div[:label "password: "
        [:input.form-control {:field :password :id :pass}]]]])

(defn login-form []
      (let [doc (r/atom {:username "Sotiris" :pass "1234"})]
           [bind-fields login-form-template doc
            (fn [id path value]
                (println @doc))]))


(defn login-page []
      (if (not (= js/window.location.pathname "/login"))
        (set! js/window.location.href "/login"))
      [:div
       [:h1 "This is the login page"]
       [login-form]])