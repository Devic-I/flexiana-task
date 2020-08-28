(ns word-scramble.core
  (:require [org.httpkit.server :as server]
            [ring.middleware.defaults :refer :all]
            [word-scramble.routes :refer :all])
  (:gen-class))

(defn -main
  "Main function used for starting up the web service on localhost:3000."
  [& args]
  (let [port (Integer/parseInt (or (System/getenv "PORT") "3000"))]
    (server/run-server (wrap-defaults #'app-routes site-defaults) {:port port})
    (println (str "Running webserver at http:/127.0.0.1:" port "/"))))
