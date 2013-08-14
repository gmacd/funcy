(ns funcy.util)

(defn log [str] (.log js/console str))

(defn deg-to-rad [degs] (* degs (/ Math/PI 180)))
