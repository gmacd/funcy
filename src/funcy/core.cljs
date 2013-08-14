(ns funcy.core
;  (:use-macros [dommy.macros :only [sel1]]
;               [game.macros :only [rgb rgba timeout]])
  (:require ;[game.input :as input]
            [funcy.graphics :refer [request-animation-frame]]
            [funcy.util :refer [log]]))
  
            ;[clojure.string :as string]
            ;[clojure.browser.repl :as repl])

(defn update []
  (log "update"))

(defn render []
  (log "render"))

; Init!
;(def body (sel1 :body))
;(def canvas (sel1 :#canvas))
;(def ctx (.getContext canvas "2d"))

;(input/init body)

(defn gameloop [i]
;  (let [curr-time-seconds (/ (Date/now) 1000)
;        frame-delta-seconds (- curr-time-seconds @last-time-seconds)]
;    (reset! last-time-seconds curr-time-seconds)
  (log (str i))
  (update)
  (render)
  (request-animation-frame (fn [] (gameloop (inc i)))))
(gameloop 0)
