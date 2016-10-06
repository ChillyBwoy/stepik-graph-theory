(ns stepik.sandbox
  (:require [prc :refer [graph]]
            [stepik.graph.settings :refer [color-blue color-red]]))

(prc/graph
  "Graph"
  {:nodes [0 1 2 3]
   :edges [[0 1] [1 2] [2 3] [3 4]]}
  {:nodes {:size 10
           :physics false}
   :edges {:width 2
           :arrows ":to"}})
