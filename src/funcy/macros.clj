(ns funcy.macros)

(defmacro rgb [r g b]
  (str "rgb(" r "," g "," b ")"))
(defmacro rgba [r g b a]
  (str "rgba(" r "," g "," b "," a ")"))

; http://keminglabs.com/c2/docs
(defmacro timeout [delay & body]
  `(js/setTimeout (fn [] ~@body) ~delay))
(defmacro interval [delay & body]
  `(js/setInterval (fn [] ~@body) ~delay))
