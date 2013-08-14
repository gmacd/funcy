(ns funcy.graphics
  (:use-macros [funcy.macros :only [timeout]]))

(def request-animation-frame
  (or (.-requestAnimationFrame js/window)
      (.-webkitRequestAnimationFrame js/window)
      #(timeout 10 (%))))

(defn clear [ctx w h colour]
  (set! (.-fillStyle ctx) colour)
  (.fillRect ctx 0 0 w h))

(defn closedPath [ctx x y pts]
  (let [[pt & pts] (partition 2 pts)]
    (.beginPath ctx)
    (.moveTo ctx (+ x (first pt)) (+ y (second pt)))
    (doseq [pt pts]
      (.lineTo ctx (+ x (first pt)) (+ y (second pt))))
    (.closePath ctx)))
