(ns stepik.euler
  (:use [clojure.java.io :only [reader]]
   :require [prc]
            [stepik.graph.core :refer [build adjacency-list adjacency-matrix]]
            [stepik.graph.settings :refer [color-blue color-red]]))

(def G (-> "/Users/e.cheltsov/Projects/stepik-graph-theory/data/euler-2.txt"
            reader
            line-seq
            build))

(use 'clojure.data)

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


(def euler-path (atom []))
(def euler-graph (atom (adjacency-list G)))

; (let [event-degree (->> euler-graph
;                         vals
;                         (map count)
;                         (every? even?))]
;   (if event-degree
;     "NONE"))

(defn find-euler-path [x]
  (loop [gx (@euler-graph x)]
    (if (= (count gx) 0)
      @euler-graph
      (let [y (first gx)]
        (do
          (swap! euler-graph update-in [x] (remove #(= y %) gx))
          (swap! euler-graph update-in [y] (remove #(= x %) gx))
          (swap! euler-path conj x)
          (recur (@euler-graph y)))))))


(find-euler-path 1)
