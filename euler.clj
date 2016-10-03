(ns stepik.euler
  (:use [clojure.java.io :only [reader]]
   :require [prc]
            [stepik.graph.core :refer [build adjacency-list adjacency-matrix]]
            [stepik.graph.settings :refer [color-blue color-red]]))

(def G (-> "/Users/e.cheltsov/Projects/stepik-graph-theory/data/euler-2.txt"
            reader
            line-seq
            build))

(prc/graph
  "Euler"
  {:nodes (:nodes G)
   :edges (:edges G)}
  {:nodes {:size 10
           :physics false}
   :edges {:width 2}})

; Euler(g, x)
;   while not g[x].isEmpty()
;     y = g[x][0]
;     g[x].remove(y)
;     g[y].remove(x)
;     euler(g, y))
;   print x



; let acc = [];
; function eulerPath (g, x) {
;   while (g[x].length) {
;     let y = g[x][0];
;     g[x] = g[x].filter(a => a !== y);
;     g[y] = g[y].filter(a => a !== x);
;     eulerPath(g, y);
;   }
;   acc.push(x);
; }
; eulerPath(G, '1');

(defn index-of [coll e]
  (first (keep-indexed #(if (= e %2) %1) coll)))

(defn remove-first-entry [arr num]
  (let [pos (index-of arr num)]
    (if (nil? pos)
      arr
      (vec (concat (subvec arr 0 pos) (subvec arr (inc pos)))))))

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

(resolve-euler-path 1)
