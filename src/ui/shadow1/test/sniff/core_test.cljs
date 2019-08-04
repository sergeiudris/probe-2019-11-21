(ns sniff.core-test
  (:require [cljs.test :as t :refer [deftest testing is]]
            [sniff.core :as core]))

(deftest one-is-one
  (testing "if one equals one"
    (is (= 1 1))))
