(ns string-calculator.core)

(use '[clojure.string])

(defn- string->number [string]
  (map read-string
       (re-seq #"[\d]+|[-d]+" string)))

(defn- filter-lower [numbers limit]
  (filter (fn [x]
            (<= x limit))
          numbers))

(defn- filter-up-to-thousand [numbers]
  (filter-lower numbers 1000))

(defn add [string]
  (let [numbers (string->number string)]
    (when-let [negative-numbers (not-empty (filter neg? numbers))]
      (throw (Exception. (str "negatives not allowed - " negative-numbers))))
    (reduce +
            (filter-up-to-thousand numbers))))
