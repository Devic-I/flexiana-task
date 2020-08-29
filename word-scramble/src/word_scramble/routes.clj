(ns word-scramble.routes
  (:require [compojure.core :refer :all]
            [word-scramble.scramble :refer [scramble?]]))

(defn scramble-route [string1 string2]
  "The entry point for the API call of the scramble functionality."
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    (scramble? string1 string2)})

(defroutes app-routes
  "Creating the endpoint for the API."
  (GET "/:string1/:string2" [string1 string2] (scramble-route string1 string2)))