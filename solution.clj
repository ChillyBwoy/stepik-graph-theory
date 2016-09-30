(use '[clojure.string :only (split)])

(defn get-pair [f s]
  (let [[x y] (split s #" ")]
    (vec (map f [x y]))))

(defn edge-as-pair-of-int [edge]
  (get-pair #(Integer/parseInt %) edge))

(defn adjacent-edges [x edges]
  (filter (fn [[a b]] (or (= a x) (= b x))) edges))

(defn adjacent-verticies [x edges]
  (-> (adjacent-edges x edges)
      flatten))

(defn zip-adjacent [nodes edges]
  (map (fn [x] (let [verts (adjacent-verticies x edges)]
                  [x (vec (remove #(= x %) verts))]))
      nodes))

(defn build-graph [data]
  (let [[v-count e-count] (edge-as-pair-of-int (first data))
        edges (map edge-as-pair-of-int (next data))
        nodes (range 1 (+ v-count 1))]
    {:nodes nodes
     :vertices (into (sorted-map) (zip-adjacent nodes edges))}))

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

(def G (build-graph (line-seq (java.io.BufferedReader. *in*))))
(println (count (dfs-all (:nodes G) (:vertices G) 1)))
