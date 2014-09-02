(ns string-calculator.core-test
  (:require [expectations :refer :all]
            [string-calculator.core :refer :all]))

;; An empty string should return 0
(expect 0 (add ""))

;; A string with number should return that number
(expect 1 (add "1"))

;; A string with two numbers (comma separated) should return their sum
(expect 2 (add "1,1"))

;; A string with unknown number of parameters (comma separated)
;; should return their sum
(expect 3 (add "1,1,1"))

;; A string containing numbers with new line between them
;; should return their sum
(expect 4 (add "1\n2,1"))

;; A different delimiter (provided as the first line in string)
;; should still allow the sum to be calculated
(expect 5 (add "//;\n1;2;2") )

;; Calling this method with a negative number
;; should result in an exception being rased, which text shows the negative number
(expect Exception (add "1,-1"))

;; Calling this method with a number greater than 1000
;; should ignore this number
(expect 2 (add "1001,2"))

;; Any length delimiter
;; should be counted as separator
(expect 6 (add "//[***]\n1***2***3"))

;; Multiple delimiters
;; should be each counted as a separator
(expect 6 (add "//[*][%]\n1*2%3"))
