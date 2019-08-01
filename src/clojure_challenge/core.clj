(ns clojure-challenge.core
  (:require [cheshire.core :as json]
            [clojure.java.io :as io]
            [clojure.string :as str]))


(def data-file (io/resource
                 "books.json"))
(defn parse-json
  "parses json from a file in resources folder"
  []
  (json/parse-string (slurp data-file)
                     (fn [k]
                       (keyword (str/lower-case k)))))

(def json-data
  (parse-json))

(type json-data)

;; gets only the name of the book
(map (fn [item]
       (get item :name))
     json-data)

;; show only books that have less than 500 pages
(filter (fn [item]
          (< (get item :numberofpages) 500)) json-data)

;; it sums the numbers of pages of all books
(reduce + (map :numberofpages json-data))

;; gets a set of mediatypes (distinct elements)
(set (map :mediatype json-data))

(defn media-paperback?
  "checkes if the mediatype is Paperback"
  [book]
  (= (:mediatype book) "Paperback"))
