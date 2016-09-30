(ns stepik.bfs
  (:use [clojure.java.io :only [reader]]
   :require [stepik.graph.core :refer [build adjacency-list]]
   :require [stepik.graph.renderer :refer [render]]))

(def G (-> "/Users/e.cheltsov/Desktop/stepik/data/bfs-2.txt"
            reader
            line-seq
            build))

(render G)

(def a-list (adjacency-list G))


[0]
[2 1] ;; +1
[[5 4 3] 2]
[[11 6] 5 4 3] ;; +2
[[8][11 6] 5 4]
[[7][8][11 6] 5]
[[7][8] 11 6]
[[7][8] 11]
[[12][7] 8] ;; +3
[[10 9][12] 7]
[[13][10 9] 12]
[[15 14][13] 10 9] ;; +4
[[15 14][13] 10]
[[15 14] 13]
[15 14]
[15]
[]


(let [g (:vertices G)
      [root adjacent] (first g)]
  (loop [visited [root]
         queue [root]]))
