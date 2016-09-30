(ns stepik.graph.renderer
  (:require [prc :refer [graph]]))

(defn render
  ([nodes edges]
   (prc/graph
    "Draw nodes and edges"
    {:nodes nodes
     :edges edges}))
  ([G]
   (prc/graph "Draw graph" G)))
