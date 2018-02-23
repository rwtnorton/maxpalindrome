(ns maxpalindrome.core-test
  (:require [clojure.test :refer :all]
            [maxpalindrome.core :as core]))

(deftest generate-index-pairs
  (are [n vs] (= (core/generate-index-pairs n) vs)
    0 #{[0 0]}
    1 #{[0 0] [0 1] [1 1]}
    2 #{[0 0] [0 1] [0 2] [1 1] [1 2] [2 2]}
    3 #{[0 0] [0 1] [0 2] [0 3] [1 3] [2 3] [3 3] [1 1] [1 2] [2 2]}
    4 #{[0 0] [0 1] [0 2] [0 3] [0 4] [1 1] [1 2] [1 3] [1 4] [2 2] [2 3] [2 4] [3 3] [3 4] [4 4]}))

(deftest palindrome?
  (are [s b] (= (core/palindrome? s) b)
    "" true
    "a" true
    "ab" false
    "aba" true
    "abba" true
    "babba" false))

(deftest generate-substrings
  (are [s vs] (= (core/generate-substrings s) vs)
    "" #{}
    "a" #{"a"}
    "ab" #{"a" "b" "ab"}
    "abc" #{"a" "b" "c" "ab" "bc" "abc"}
    "abb" #{"a" "b" "ab" "bb" "abb"}
    "aaa" #{"a" "aa" "aaa"}))

(deftest find-max-palindrome
  (are [s mp] (= (core/find-max-palindrome s) mp)
    "" nil
    "a" "a"
    "aa" "aa"
    "ab" "a"
    "aba" "aba"
    "xohioihoy" "ohioiho"
    "abcdefghi" "a"))
