# naxels/getpocket-api

Library to work with the GetPocket API

## Pre-usage
- Create a new app in Pocket in order to use the API
- Authenticate yourself with Pocket in order to receive an access token
  - https://getpocket.com/developer/docs/authentication

# Installation
*getpocket-api* is available as a Maven artifact from [Clojars](http://clojars.org/naxels/getpocket-api).

![Latest version](https://clojars.org/naxels/getpocket-api/latest-version.svg)

## Usage
An example usecase to print all resolved titles from the response:

```clojure
(:require '[naxels.getpocket.api :as p])

(let [p-opts (p/common-required-parameters [(:consumer-key "replace")
                                            (:access-token "replace")])
      retrieve-payload (p/payload p-opts p/retrieve-optional)
      items (-> (p/retrieve retrieve-payload)
                (get :list))]
    (println (str "Found " (count items) " items"))
    (doseq [[_id item] items]
      (println (str "- " (:resolved_title item)))))
```

Note that the payload data is all maps, for example the p/retrieve-optional is:
```clojure
{:state "unread"
 :count "10"
 :detailType "complete"}
```

This library has support for these [GetPocket API's](https://getpocket.com/developer/docs/overview):
- [Add](https://getpocket.com/developer/docs/v3/add) (p/add)
- [Modify](https://getpocket.com/developer/docs/v3/modify) (p/modify)
- [Retrieve](https://getpocket.com/developer/docs/v3/retrieve) (p/retrieve)

This library does not support the authentication & other API's

Once you have setup the common required parameters (consumer_key & access_token) as in the example above you can use them to build the payload for your request and the map's will be automatically merged.

## License

Distributed under the Eclipse Public License version 1.0.
