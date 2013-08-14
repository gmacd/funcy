(ns funcy.input
  (:use-macros [dommy.macros :only [sel1]])
  (:require [dommy.core :as dom]))

; TODO
; Map of non-:up keys rather than an array of all keys?

; Atom to store the state of each keycode (used as array idx)
; State is one of :up, :pressed, :held, :released
(def keys-state
  (atom (vec (take 256 (repeat :up)))))

(defn- set-key-state [key-code new-state]
  (swap! keys-state #(assoc @keys-state key-code new-state)))
(defn- get-key-state [key-code]
  (nth @keys-state key-code))

(defn- handle-key [evt evt-state]
  "Handle keypress.  Transition can be up->pressed->held->released->up
   or up->pressed->released->up.  All transitions occur on key events, except
   from released->up, which has to be handled manually."
  (let [key-code (.-keyCode evt)
        old-state (get-key-state key-code)]
    (set-key-state
      key-code
      (cond
        ; up -> pressed or up
        (= old-state :up) (if (= evt-state :up) :up :pressed)
        ; pressed -> held or released
        (= old-state :pressed) (if (= evt-state :up) :released :held)
        ; held -> held or released
        (= old-state :held) (if (= evt-state :up) :released :held)
        ; released -> pressed or up
        (= old-state :released) (if (= evt-state :up) :up :pressed)))))

(defn get-keys-state-for-frame []
  "Return a clone of the current keys state"
  (vec @keys-state))

(defn- transition-key-states [frame-keys]
  "Transition pressed to held, and released to up, so they're only set for one
   frame."
  (vec (map #(cond
              (= % :pressed) :held
              (= % :released) :up
              :else %) frame-keys)))

(defn update-keys-state-for-frame []
  (swap! keys-state transition-key-states))

(defn down? [keys key-code]
  "Returns true if not :up"
  (not= (nth keys key-code) :up))

(defn init [element]
  "Setup event handlers"
  (dom/listen! element :keydown #(handle-key % :down))
  (dom/listen! element :keyup #(handle-key % :up))
  (dom/listen! element :keypress #(handle-key % :press)))

; TODO keywords are functions - change from def's to keywords?
(def left 37)
(def right 39)
(def up 38)
(def down 40)
