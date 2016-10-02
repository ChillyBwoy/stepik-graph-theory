(ns stepik.sandbox
  (:require [prc :refer [graph]]))

(def v-red
  (map #(keyword (str "n" %)) (range 1 9)))

(def v-blue
  (map #(keyword (str "m" %)) (range 1 24)))

(def edges
  (apply concat
    (for [n v-red]
      (for [m v-blue]
        [n m]))))

(prc/graph
  "Lol"
  {:nodes (concat (for [x v-red]
                    {:id x
                     :label x
                     :group :red})
                  (for [y v-blue]
                    {:id y
                     :label y
                     :group :blue}))
   :edges edges}
  {:nodes {:shape "database"
           :size 10
           :physics false}
   :edges {:width 2}
   :groups {:red {:color {:background "red"}}
            :blue {:color {:background "blue"}}}})
