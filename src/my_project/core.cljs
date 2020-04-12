(ns my-project.core
  (:require [reagent.core :as reagent :refer [atom]]
            [reitit.frontend :as rf]
            [reitit.frontend.easy :as rfe]
            [reitit.coercion.spec :as rss]
            [spec-tools.data-spec :as ds]
            [fipp.edn :as fedn]
            [my-project.pages.about :as about]
            [my-project.pages.login :as login]
            [my-project.state.state :as state :refer [app-state]]))

(defn hello-world []
  [:div
   [:h1 (:text @app-state)]
   [:h3 "Edit and watch it change!"]])

(defn current-page
  "This is the main container of the application. It loads the corresponding page"
  []
  [:div
   (if-let [user (:user @app-state)]
     ;; user is there so get match parameter from @app-state atom 
     (let [match (get-in @app-state [:urlhelpers :match])]
       (if match
         (let [view (:view (:data match))]
           [:div
            [:h3 "Hello " user ","]
            [:ul
             [:li [:a {:href (rfe/href ::home)} "Home"]]
             [:li [:a {:href (rfe/href ::about)} "About"]]]
            [view match]])
         [:h1 "Not found"]))
     [login/login-page])
   [:pre (with-out-str (fedn/pprint (get-in @app-state [:urlhelpers :match])))]])

(def routes
  [["/" {:name ::home :view hello-world}]
   ["/login" {:name ::login :view login/login-page}]
   ["/about" {:name ::about :view about/about-page :public? false}]])

(defn start []
  (rfe/start!
    (rf/router routes {:data {:coercion rss/coercion}})
    (fn [m] (swap! app-state assoc-in [:urlhelpers :match] m))
    {:use-fragment false})
    (reagent/render-component [current-page]
      (. js/document (getElementById "app"))))

(defn ^:export init []
  ;; init is called ONCE when the page loads
  ;; this is called in the index.html and must be exported
  ;; so it is available even in :advanced release builds
  (start))

(defn stop []
  ;; stop is called before any code is reloaded
  ;; this is controlled by :before-load in the config
  (js/console.log "stop"))
