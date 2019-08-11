(ns figmain1.core-test
  (:require [cljs.test :refer-macros [deftest is run-tests]]))

(deftest should-not-pass
  (is (= 1 20)))

(run-tests)