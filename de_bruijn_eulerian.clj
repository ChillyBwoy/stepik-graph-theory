(ns stepik.de-bruijn-eulerian
  (:require [prc :refer [graph]]))

(defn suffix [s n]
  (subs s (- (count s) n)))

(defn prefix [s n]
  (->> (clojure.string/split s #"")
       (take n)
       (clojure.string/join #"")))

(defn nodes-seq [n k]
  (let [k' (dec k)
        size (Math/pow n k')]
    (for [x (range size)]
      (let [s (Integer/toString x n)
            f (format (str "%" k' "s") s)]
          (clojure.string/replace f #" " "0")))))


(defn reduce-adjacency [nodes v n]
  (reduce
    (fn [acc x]
      (if (= (suffix v n) (prefix x n))
        (conj acc {:from v :to x :label (str v (last x))})
        acc))
    []
    nodes))


(defn edges-seq [n k nodes]
  (loop [x (first nodes)
         xs (rest nodes)
         edges []]
    (if (nil? x)
      edges
      (let [adjacency (reduce-adjacency nodes x (- k 2))]
        (recur (first xs)
               (next xs)
               (apply conj edges adjacency))))))


(count (edges-seq 2 4 (nodes-seq 2 4)))

(let [n 2
      k 4
      nodes (nodes-seq n k)
      edges (edges-seq n k nodes)]
    (prc/graph
      "Graph"
      {:nodes nodes
       :edges edges}
      {:nodes {:size 10
               :physics false
               :shape "circle"}
       :edges {:width 2
               :arrows ":to"}}))
