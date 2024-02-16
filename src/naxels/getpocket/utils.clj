(ns naxels.getpocket.utils
  (:require [clojure.string :as str]
            [clj-http.client :as http]
            [jsonista.core :as json]))

(defn build-url
  "Combine the URL parts"
  [url-parts]
  (str/join "/" url-parts))

(defn map->json
  [m]
  (json/write-value-as-string m))

(defn http-retrieve
  [url request_body]
  (let [resp (http/post url {:body request_body
                             :content-type :json})]
    (when (= 200 (:status resp))
      (json/read-value (:body resp)
                       (json/object-mapper {:decode-key-fn true})))))
