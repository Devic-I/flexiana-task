(ns word-scramble.scramble)

(defn is-string-valid?
  "Function returns true if the string is containing only lower case
  letters and no spaces, else returns false."
  [string]
  (every? true? (map #(Character/isLowerCase %) string)))

(defn get-error-message
  "Generator for the error message if the string does not satisfy criterion in
  the function is-string-valid?."
  [string]
  (str string " can only contain lower case letters with no spaces."))

(defn get-letter-map
  "Function which returns frequencies of a single letter inside a string.
  Frequencies are stored as the Hash map, while the keys of the map are represented
  as keywords insted of single letter strings (a to :a).
  This Hash map will be called letter map onwards."
  [string]
  (frequencies (map (comp keyword str) string)))

(defn compare-letter-maps
  "The present function receives two letter maps and performs comparison of values between the two.
  We take every key from the second letter map and look whether the first letter map contains
  a lower value for that particular key. If it occurs for any single key, the function will return false,
  else it will return true."
  [target-key letter-map1 letter-map2]
  (let [count1 (target-key letter-map1)
        count2 (target-key letter-map2)]
    (if (or (nil? count1) (< count1 count2))
      false
      true)))

(defn scramble? [string1 string2]
  "Wrapper function for all of aforementioned functionalities. The function takes two strings and performs
  validity check on both of them. If any of strings fail the validity check, the error message will be returned.
  If not, this function will check whether string2 can be recreated by scrambling string1.
  String containing descriptive answer will be returned."
  (if (is-string-valid? (str string1 string2))
    (let [letter-map1 (get-letter-map string1)
          letter-map2 (get-letter-map string2)
          letter-coll (keys letter-map2)]
      (if (every? true? (map #(compare-letter-maps % letter-map1 letter-map2) letter-coll))
        (str "String " string2 " can be scrambled from the portion of the string " string1 ".")
        (str "String " string2 " cannot be scrambled from the portion of the string " string1 ".")))
    (if (is-string-valid? string1)
      (get-error-message string2)
      (get-error-message string1))))
