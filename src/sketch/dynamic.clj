(ns sketch.dynamic
  (:require [clojure.pprint :as pretty]
            [quil.core :as quil]))

(def colours {:base03 [193 100 21]
              :base02 [192 90 26]
              :base01 [194 25 46]
              :base00 [195 23 51]
              :base0 [186 13 59]
              :base1 [180 9 63]
              :base2 [44 11 93]
              :base3 [44 10 99]

              :yellow [45 100 71]
              :orange [8 89 80]
              :red [1 79 86]
              :magenta [331 74 83]
              :violet [237 45 77]
              :blue [205 82 82]
              :cyan [175 74 63]
              :green [68 100 60]
              
              :black [0 0 0]})

(defn save-frame-to-disk
  ([]
   (quil/save-frame (pretty/cl-format nil
                                      "frames/~d-~2,'0d-~2,'0d-~2,'0d-~2,'0d-~2,'0d-####.jpeg"
                                      (quil/year) (quil/month) (quil/day)
                                      (quil/hour) (quil/minute) (quil/seconds))))
  ([state _]
   (save-frame-to-disk)
   state))

(defn- stroke
  [colour]
  (quil/stroke 1 (quil/random 50 100) (rand 100) (rand)#_(flatten (conj (colours colour) (rand)))))

(defn- background
  [colour]
  (apply quil/background (colours colour)))

(defn- arc
  []
  (let [j 5
        w (+ (quil/random (* (quil/width)
                             2))
             (quil/random (- j)
                          j))
        h (+ (quil/random (* (quil/height)
                             2))
             (quil/random (- j)
                          j))
        x (quil/random (- j)
                       j)
        y (quil/random (- j)
                       j)]
    (stroke :red)
    (quil/arc x
              y
              w
              h
              (quil/random quil/TWO-PI)
              (quil/random quil/TWO-PI))))

(defn- arcs
  [n]
  #_(stroke :red)
  (quil/with-translation [(/ (quil/width)
                             2)
                          (/ (quil/height)
                             2)]
    (dotimes [_ n]
      (arc))))

(defn draw
  []
  (quil/no-loop)
  (quil/no-fill)
  (background :black)
  (arcs 999)
  (save-frame-to-disk))

(defn initialise
  []
  (quil/smooth)
  (quil/color-mode :hsb 360 100 100 1.0))
