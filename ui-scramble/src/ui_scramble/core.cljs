(ns ui-scramble.core
  (:require [reagent.core :as r]
            [reagent.dom :as rdom]
            [goog.dom :as gdom]
            [cljs-http.client :as http]
            [cljs.core.async :refer [<! go]]))

(enable-console-print!)

(def api-response (r/atom nil))

(defn get-request
  "Function which will execute GET request and update atom api-response with the respective result."
  [string1 string2]
  (go
    (let [response (<! (http/get (str "http://localhost:3000/" string1 "/" string2)
                                 {:headers {"Access-Control-Allow-Headers" "Content-Type"
                                            "Access-Control-Allow-Origin" "*"
                                            "Access-Control-Allow-Credentials" "true"
                                            "Content-type" "text/html"}
                                  :with-credentials? false}))]
      (reset! api-response response))))

(defn string-input [value]
  "Textbox implementation. By inducing single change inside the present textbox, the corresponding string atom
  will be updated."
  [:input {:type "text"
           :value @value
           :on-change #(reset! value (-> % .-target .-value))}])

(defn render-response
  "Function for rendering the response received from the backend."
  []
  (fn []
    (let [response-text (if @api-response (:body @api-response) "Press scramble button")]
      [:div
       [:p response-text]])))

(defn ui-fn []
  "Wrapper function which will launch two textboxs and provide button for executing scrambling functionality."
  (let [string1 (r/atom "")
        string2 (r/atom "")]
    (fn []
      [:div
       [:p "Enter the word from which the scramble will be performed:"]
       [string-input string1]
       [:p "Enter the target scrambled word:"]
       [string-input string2]
       [:p [:input {:type "button" :value "Scramble"
                    :on-click #(get-request @string1 @string2)}]]
       [render-response]])))

(rdom/render (ui-fn) (gdom/getElement "app"))
