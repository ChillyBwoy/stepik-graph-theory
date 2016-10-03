(ns stepik.sandbox
  (:require [prc :refer [graph]]
            [stepik.graph.settings :refer [color-blue color-red]]))

(prc/graph
  "Euler"
  {:nodes [1 2 3 4 5 6 7]
   :edges [{:from 1 :to 2 :color color-blue}
           {:from 2 :to 3 :color color-red}
           {:from 3 :to 4 :color color-blue}
           {:from 4 :to 1 :color color-red}
           {:from 1 :to 5 :color color-blue}
           {:from 5 :to 4 :color color-red}
           {:from 4 :to 7 :color color-blue}
           {:from 7 :to 6 :color color-red}
           {:from 6 :to 5 :color color-blue}
           {:from 5 :to 2 :color color-red}
           {:from 2 :to 7 :color color-blue}
           {:from 7 :to 3 :color color-red}
           {:from 3 :to 6 :color color-blue}
           {:from 6 :to 1 :color color-red}]}
  {:nodes {:size 10
           :physics false}
   :edges {:width 2}})
