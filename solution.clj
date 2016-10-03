(use '[clojure.string :only (split)])

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
      flatten))

(defn zip-adjaceny [nodes edges]
  (map (fn [x] (let [verts (adjaceny-verticies x edges)]
                  [x (vec (remove #(= x %) verts))]))
      nodes))

(defn adjacency-list
  ([g]
   (adjacency-list (:nodes g) (:edges g)))
  ([nodes edges]
   (into (sorted-map) (zip-adjaceny nodes edges))))

(defn index-of [coll e]
  (first (keep-indexed #(if (= e %2) %1) coll)))

(defn remove-first-entry [arr num]
  (let [pos (index-of arr num)]
    (if (nil? pos)
      arr
      (vec (concat (subvec arr 0 pos) (subvec arr (inc pos)))))))

(def G (build (line-seq (java.io.BufferedReader. *in*))))

(def euler-path (atom []))
(def euler-graph (atom (adjacency-list G)))

(defn find-euler-path [x]
  (while (> (count (@euler-graph x)) 0)
    (do
      (let [gx (@euler-graph x)
            y (first gx)
            gy (@euler-graph y)]
        (swap! euler-graph assoc x (remove-first-entry gx y))
        (swap! euler-graph assoc y (remove-first-entry gy x))
        (find-euler-path y))))
  (swap! euler-path conj x))

(defn resolve-euler-path [x]
  (let [event-degree (->> @euler-graph
                          vals
                          (map count)
                          (every? #(and (> % 0) (even? %))))]
      (if event-degree
        (find-euler-path x)
        "NONE")))

(let [r (resolve-euler-path 1)]
  (if (vector? r)
    (println (clojure.string/join " " (pop r)))
    (println r)))
