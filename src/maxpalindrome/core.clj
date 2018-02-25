(ns maxpalindrome.core
  (:require [clojure.string :as str])
  (:gen-class))

(defn generate-index-pairs
  [n]
  (->>
   (for [i (range (inc n))
         j (reverse (range (inc n)))
         :when (<= i j)]
     [i j])
   (into #{})))

(defn palindrome?
  [s]
  (= s (str/reverse s)))

(defn generate-substrings
  [s]
  (->> (generate-index-pairs (count s))
       (map (fn [[i j]] (subs s i j)))
       (remove empty?)
       (into #{})))

(defn find-all-palindromes
  [s]
  (->> (generate-substrings s)
       (filter palindrome?)
       (sort-by count >)))

(defn find-max-palindrome
  [s]
  (let [vs (find-all-palindromes s)]
    (if-not (seq vs)
      (first vs)
      (let [v (first vs)]
        (->> vs
             (filter (fn [x] (= (count x) (count v))))
             (sort)
             (first))))))

(defn -main
  [& args]
  (let [s (first args)]
    (println (find-max-palindrome s))))
