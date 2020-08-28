(ns word-scramble.core-test
  (:require [clojure.test :refer :all]
            [word-scramble.core :refer :all]
            [word-scramble.scramble :refer :all]))
(deftest is-string-valid?-unit-test
  (testing "Failure in validitiy unit tests."
    (is (is-string-valid? "katas"))
    (is (is-string-valid? "steak"))
    (is (is-string-valid? (str "steak" "katas")))
    (is (not (is-string-valid? "ka as")))
    (is (not (is-string-valid? "ka1as")))
    (is (not (is-string-valid? "kaTas")))))

(deftest scramble?-unit-test
  (testing "Failure in scramble unit tests."
    (is (= (scramble? "rekqodlw" "world") "String world can be scrambled from the portion of the string rekqodlw."))
    (is (= (scramble? "cedewaraaossoqqyt" "codewars") "String codewars can be scrambled from the portion of the string cedewaraaossoqqyt."))
    (is (= (scramble? "katas" "steak") "String steak cannot be scrambled from the portion of the string katas."))
    (is (= (scramble? "kaTas" "steak") (get-error-message "kaTas")))
    (is (= (scramble? "katas" "s eak") (get-error-message "s eak")))
    (is (= (scramble? "ka1as" "steak") (get-error-message "ka1as")))))
