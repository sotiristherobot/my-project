(ns my-project.pages.about
  (:require [goog.string :as gstring]))

(defn get-random-card 
  "Returns a random card from the collection passed to this function"
  [col] (rand-nth col))

(defn about-page []
      [:div
       [:h1 "This is the about page"]
       (let [playing-cards ["&#9824" "&#9827" "&#9829" "&#9830"]]
         [:p (gstring/unescapeEntities (get-random-card playing-cards))])])