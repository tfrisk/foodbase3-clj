(ns foodbase.db
	(:require [taoensso.carmine :as car :refer (wcar)]))

;Redis connection
(def server1-conn {
	:pool {}
	:spec {:host "redishost" :port 6379}})
(defmacro wcar* [& body] `(car/wcar server1-conn ~@body))

;Redis wrappers
(defn redis-get
	"Get data from database"
	[dbkey]
	(wcar* (car/get dbkey)))

(defn redis-set
	"Save data to database"
	[dbkey dbval]
	(wcar* (car/set dbkey dbval)))

(defn save-item-map
	"Save item, uses :id keyword as database key. Be sure that data contains :id keyword."
	[data]
	(redis-set (:id data) data))

(defn load-item-by-id
	"Load item by id"
	[id]
	(redis-get (str id)))

(defn load-manufacturer-list
	"Load list of all manufacturers"
	[]
	(redis-get "manufacturerlist"))

(defn save-manufacturer-list
	"Save list of all manufacturers"
	[newlist]
	(redis-set "manufacturerlist" newlist))

(defn load-ingredient-list
	"Load list of all ingredients"
	[]
	(redis-get "ingredientlist"))

(defn save-ingredient-list
	"Save list of all ingredients"
	[newlist]
	(redis-set "ingredientlist" newlist))

(defn load-food-list
	"Load list of all foods"
	[]
	(redis-get "foodlist"))

(defn save-food-list
	"Save list of all foods"
	[newlist]
	(redis-set "foodlist" newlist))

;Old entries for reference

(def old-manufacturerlist [
	{ :id "m-6bcf5b66-6072-42db-92d1-842b76cd0343" :name "Arla Ingman" }
	{ :id "m-c92aac9e-22ae-4645-8cc3-4513d5582449" :name "Fazer" }
	{ :id "m-a5ca74fa-c538-452f-99a3-b6e3c9395f07" :name "Valio" }
])

(def old-ingredientlist [
	{ :id "i-eb191222-108e-4826-a736-fa99faf29db6" :name "pastöroitu kerma" }
	{ :id "i-fd7b1fa5-9069-4f85-ab96-1529ab6a83ef" :name "muunnettu maissitärkkelys" }
	{ :id "i-92524e01-16be-4b81-a05b-2ca6ac0bc4ea" :name "pektiini" }
	{ :id "i-b7bd971a-5e38-4f3d-94c1-bb537826942d" :name "hapate" }
	{ :id "i-292de23b-7620-4a7e-bda0-d088bd6c3742" :name "sokeri" }
	{ :id "i-5de0e380-f2ad-4a8f-821e-3907a7202ffc" :name "glukoosisiirappi" }
	{ :id "i-a3114042-7335-4524-8434-3ac77fd58abf" :name "maissitärkkelys" }
	{ :id "i-1443790a-4a71-4813-8d66-b9d0e8d339eb" :name "stabilointiaine (E420)" }
	{ :id "i-fc0544f5-4992-453d-a49a-0f6dfcd587a2" :name "liivate" }
	{ :id "i-8521e973-ace1-491a-bd68-fc1c969e681e" :name "muunnettu tärkkelys" }
	{ :id "i-bfbe800c-d1f8-435b-a91d-bad6fa833460" :name "happamuudensäätöaine (E330, E331)" }
	{ :id "i-370b8b91-e9be-434b-9510-3dc6c9b0ab0a" :name "lakritsiuute" }
	{ :id "i-8f64bcd6-15fe-4c19-8969-6c092ba322db" :name "suola" }
	{ :id "i-2c9ad1cc-4dda-43d7-8695-c2f1fed369ba" :name "aromit" }
	{ :id "i-c287477a-72ca-476b-ab6f-41c0b552b45d" :name "pintakäsittelyaine (E903)" }
	{ :id "i-9c5bbf35-4fde-4cb6-a19e-2aa3dcc0fa59" :name "värit (E153, E120, E160a, E131, E100)" }
	{ :id "i-47343e09-9952-4de9-9ec4-92feb56e5e64" :name "pastöroitu maito" }
	{ :id "i-64adb208-d30c-4ec4-b484-6d43f2a973cf" :name "maitoproteiini" }
	{ :id "i-1b9ae59c-1ad6-4748-a011-4b12838c460f" :name "stabilointiaine karrageeni" }
])

(def old-foodlist [
	{ :id "f-3cfd0d6f-3d03-4908-9d98-b6ffe1783cac" :name "Kevyt Créme Fraíche eila laktoositon" }
	{ :id "f-0b353afa-475d-4df7-994d-5eb1cb89da48" :name "Vispikerma eila laktoositon" }
	{ :id "f-30e5f2a4-5263-4477-a337-857919de4308" :name "Angry Birds makeissekoitus" }
	{ :id "f-80ad49f1-a416-4e04-b268-982a815f4fe7" :name "Turkkilainen jogurtti" }
])
