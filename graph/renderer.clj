(ns stepik.graph.renderer
  (:require [prc :refer [graph]]
            [stepik.graph.settings :refer [color-blue color-red]]))


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
      :groups {:red {:color {:background color-red}}
               :blue {:color {:background color-blue}}}}))
  ([G]
   (render (:nodes G) (:edges G))))
