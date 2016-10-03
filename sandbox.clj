(ns stepik.sandbox
  (:require [prc :refer [graph]]
            [stepik.graph.settings :refer [color-blue color-red]]))

(def edges [
            ; {:from 1 :to 4 :color color-red}
            {:from 4 :to 3 :color "#FF00FF"}
            ; {:from 3 :to 6 :color color-red}
            {:from 6 :to 7 :color "#FFFF00"}
            {:from 7 :to 8 :color "#FFFF00"}
            ; {:from 8 :to 5 :color color-red}
            {:from 5 :to 4 :color "#0000FF"}
            {:from 4 :to 11 :color "#0000FF"}
            {:from 11 :to 10 :color "#0000FF"}
            {:from 10 :to 5 :color "#0000FF"}
            {:from 5 :to 6 :color "#0000FF"}
            {:from 6 :to 10 :color "#0000FF"}
            {:from 10 :to 9 :color "#0000FF"}
            {:from 9 :to 7 :color "#0000FF"}
            ; {:from 7 :to 2 :color color-red}
            {:from 2 :to 3 :color "#00FF00"}
            {:from 3 :to 11 :color "#00FF00"}
            {:from 11 :to 12 :color "#00FF00"}
            {:from 12 :to 9 :color "#00FF00"}
            {:from 9 :to 8 :color "#00FF00"}
            {:from 8 :to 1 :color "#00FF00"}
            {:from 1 :to 2 :color "#00FF00"}
            {:from 2 :to 12 :color "#00FF00"}
            {:from 12 :to 1 :color "#00FF00"}])




(prc/graph
  "Euler"
  {:nodes [1 2 3 4 5 6 7 8 9 10 11 12]
   :edges edges}
  {:nodes {:size 10
           :physics false}
   :edges {:width 2}})


{1, 4, 3, 6, 7, 8, 5, 4, 11, 10, 5, 6, 10, 9, 7, 2, 3, 11, 12, 9, 8, 1, 2, 12, 1}

{4, 3}
{6, 7, 8}
{5, 4, 11, 10, 5, 6, 10, 9, 7}
{2, 3, 11, 12, 9, 8, 1, 2, 12, 1}
