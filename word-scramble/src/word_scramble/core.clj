(ns word-scramble.core
  (:require [org.httpkit.server :as server]
            [ring.middleware.defaults :refer :all]
            [ring.middleware.resource :as res]
            [ring.middleware.cors :as cors]
            [word-scramble.routes :refer :all])
  (:gen-class))

(defn configure-server
  "Server configuration is provided inside the present function. The creation of endpoints,
  along with providing access to the frontend is provided by executing."
  []
  (-> (wrap-defaults #'app-routes site-defaults)
      (cors/wrap-cors :access-control-allow-origin [#".*"]
                      :access-control-allow-methods [:get :put :post :delete])
      (res/wrap-resource "public")
      (res/wrap-resource "/META-INF/resources")))

(defn -main
  "Main function used for starting up the web service on localhost:3000."
  [& args]
  (let [port (Integer/parseInt (or (System/getenv "PORT") "3000"))]
    (server/run-server (configure-server) {:port port})
    (println (str "Running webserver at http:/127.0.0.1:" port "/"))))
