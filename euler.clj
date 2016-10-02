(ns stepik.euler
  (:use [clojure.java.io :only [reader]]
   :require [stepik.graph.core :refer [build adjacency-list]]
   :require [stepik.graph.renderer :refer [render]]))

(def G (-> "/Users/e.cheltsov/Projects/stepik-graph-theory/data/euler-2.txt"
            reader
            line-seq
            build))

(render G)

; Euler(g, x)
;       while not g[x].isEmpty()
;           y = g[x][0]
;           g[x].remove(y)
;           g[y].remove(x)
;           euler(g, y))
; print x
