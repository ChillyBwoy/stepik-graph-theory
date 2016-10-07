(ns stepik.de-bruijn-eulerian
  (:require [prc :refer [graph]]))

(defn suffix [s n]
  (subs s (- (count s) n)))

(defn prefix [s n]
  (->> (clojure.string/split s #"")
       (take n)
       (clojure.string/join #"")))

(defn nodes-seq [n k]
  (let [size (Math/pow n k)]
    (set (for [x (range size)]
            (let [s (Integer/toString x n)
                  f (format (str "%" k "s") s)]
              (prefix (clojure.string/replace f #" " "0") n))))))


(defn reduce-adjacency [n nodes v]
  (let [suff (last v)]
    (reduce
      (fn [acc x]
        (if (= suff (first x))
          (conj acc {:from v :to x :label (str v (last x))})
          acc))
      []
      nodes)))


(defn edges-seq [n k nodes]
  (loop [x (first nodes)
         xs (rest nodes)
         edges []]
    (if (nil? x)
      edges
      (let [suff (last x)
            adjacency (reduce-adjacency n nodes x)]
        (recur (first xs)
               (next xs)
               (apply conj edges adjacency))))))

(let [n 2
      k 3
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
