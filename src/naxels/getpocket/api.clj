(ns naxels.getpocket.api
   (:require [naxels.getpocket.utils :as u]))

 (def base-url "https://getpocket.com")
 (def v "v3")

 (defn- call
   [endpoint payload]
   (let [url (u/build-url [base-url v endpoint])]
     (u/http-retrieve url payload)))

;; API Endpoints

 (defn retrieve
   "Retrieve
    https://getpocket.com/developer/docs/v3/retrieve"
   [payload]
   (call "get" payload))

 (defn add
   "Add
    - additional required parameter: url

    https://getpocket.com/developer/docs/v3/add"
   [payload]
   (call "add" payload))

 (defn modify
   "Modify
    - additional required parameter: actions

    https://getpocket.com/developer/docs/v3/modify"
   [payload]
   (call "send" payload))

;; Parameters

(defn common-required-parameters
  "Required parameters
   - consumer_key
   - access_token"
  [[consumer-key access-token]]
  {:consumer_key consumer-key
   :access_token access-token})

;; Some default optional parameters
(def retrieve-optional {:state "unread"
                        :count "10"
                        :detailType "complete"})

;; Payload builder
(defn payload
  "Build the JSON payload
   containing the required parameters and additional (if any) parameter(s)
   both need to be a map"
  ([required-parameters] (u/map->json required-parameters))
  ([required-parameters parameter-map] (u/map->json (merge required-parameters
                                                           parameter-map))))
