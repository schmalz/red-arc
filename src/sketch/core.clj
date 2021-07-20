(ns sketch.core
  (:require [quil.core :as quil]
            [sketch.dynamic :as dynamic])
  (:gen-class))

(quil/defsketch sketch
  :title "Red Arc"
  :setup dynamic/initialise
  :draw dynamic/draw
  :features [:keep-on-top]
  :size [900 900])

(defn refresh
  []
  (use :reload 'sketch.dynamic)
  (.loop sketch))
