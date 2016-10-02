(ns stepik.graph.renderer
  (:require [prc :refer [graph]]))

(defn render
  ([nodes edges]
   (prc/graph
    "Draw nodes and edges"
    {:nodes nodes
     :edges edges}
    {:nodes {:shape "circle"
             :size 10
             :physics false}
      :edges {:width 2}
      :groups {:red {:color {:background "red"}}
               :blue {:color {:background "blue"}}}}))
  ([G]
   (render (:nodes G) (:edges G))))
