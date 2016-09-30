(ns stepik.graph.core
  (:require [clojure.string :refer [split]]))


(defn- get-pair [s]
  (let [[x y] (split s #" ")]
    (vec (map #(Integer/parseInt %) [x y]))))


(defn build [data]
  (let [edges (map #(get-pair %) (next data))
        nodes (flatten edges)]
    {:nodes (set nodes)
     :edges edges}))


(defn adjacency-edges [x edges]
 (filter (fn [[a b]] (or (= a x) (= b x))) edges))


(defn adjaceny-verticies [x edges]
  (-> (adjacency-edges x edges)
      flatten
      set))


(defn zip-adjaceny [nodes edges]
  (map (fn [x] (let [verts (adjaceny-verticies x edges)]
                  [x (vec (remove #(= x %) verts))]))
      nodes))

(defn adjacency-list
  ([g]
   (adjacency-list (:nodes g) (:edges g)))
  ([nodes edges]
   (into (sorted-map) (zip-adjaceny nodes edges))))


(defn adjacency-matrix
  ([g]
   (adjacency-matrix (:nodes g) (:edges g)))
  ([nodes edges]
   (for [x nodes]
     (for [y nodes]
       (let [verts (adjaceny-verticies x edges)
             adj (vec (remove #{x} verts))]
            (if (some #(= y %) adj)
                1
                0))))))
