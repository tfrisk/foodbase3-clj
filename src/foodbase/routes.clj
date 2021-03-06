(ns foodbase.routes
	(:use
		compojure.core
		[foodbase.web :as web]
		[hiccup.middleware :only (wrap-base-url)])
	(:require
		[compojure.route :as route]
		[compojure.handler :as handler]
		[compojure.response :as response]))

(defroutes app-routes
	(GET "/" [] (web/index-page))
	(GET "/details/:id" [id] (web/detail-page id))
	(POST "/search" {params :params} (web/search-page params))
	(GET "/new/food" [] (web/new-food-form ""))
	(POST "/new/food" {params :params} (web/new-food-form params))
	(GET "/new/ingredient" [] (web/new-ingredient-form ""))
	(POST "/new/ingredient" {params :params} (web/new-ingredient-form params))
	(GET "/new/manufacturer" [] (web/new-manufacturer-form ""))
	(POST "/new/manufacturer" {params :params} (web/new-manufacturer-form params))
	(route/not-found "Page not found"))

(def app
	(-> (handler/site app-routes)
		(wrap-base-url)))
