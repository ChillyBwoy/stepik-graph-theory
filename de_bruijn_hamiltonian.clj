(ns stepik.de-bruijn-hamiltonian
  (:require [prc :refer [graph]]))

(defn suffix [s n]
  (subs s (- (count s) n)))

(defn prefix [s n]
  (->> (clojure.string/split s #"")
       (take n)
       (clojure.string/join #"")))

(defn nodes-seq [n k]
  (let [size (Math/pow n k)]
    (vec (for [x (range size)]
          (let [s (Integer/toString x n)
                f (format (str "%" k "s") s)]
              (clojure.string/replace f #" " "0"))))))

(defn edge-map [a b n]
  (let [as (suffix a n)
        bp (prefix b n)]
    (if (= as bp)
      {:from a :to b :label as}
      {:from b :to a :label as})))

(defn edges-seq [n k nodes]
  (let [size (dec k)]
    (loop [x (first nodes)
           xs (rest nodes)
           edges []]
      (if (nil? x)
        edges
        (let [suff (suffix x size)
              adjacency (filter #(= suff (prefix % size)) nodes)]
          (recur (first xs)
                 (next xs)
                 (apply conj edges (map #(edge-map x % size) adjacency))))))))

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

; 0000
; 0001
; 0010
; 0100
; 1001
; 0011
; 0110
; 1101
; 1010
; 0101
; 1011
; 0111
; 1111
