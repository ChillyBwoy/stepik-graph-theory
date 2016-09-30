(ns stepik.dfs
  (:use [clojure.java.io :only [reader]]
   :require [stepik.graph.core :refer [build adjacency-list]]
   :require [stepik.graph.renderer :refer [render]]))

(def G (-> "/Users/e.cheltsov/Desktop/stepik/data/dfs-2.txt"
            reader
            line-seq
            build))

(render G)

(defn dfs [g s]
  (loop [visited #{s}
         frontier [s]
         path []]
    (if (empty? frontier)
      path
      (let [v (peek frontier)
            adj (g v)]
        (recur
          (into visited adj)
          (into (pop frontier) (remove visited adj))
          (conj path v))))))

(defn dfs-all [n g s]
  (loop [nodes n
         result []]
    (if (= (count nodes) 0)
        result
        (let [step (dfs g (first nodes))]
          (recur (remove (set step) nodes)
                 (conj result step))))))

(dfs-all (:nodes G) (adjacency-list G) 0)
