(defproject funcy "0.1.0-SNAPSHOT"
  :description "Functional game thing"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :source-paths ["src"]
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [prismatic/dommy "0.1.1"]]

  ;; lein-cljsbuild plugin to build a CLJS project
  :plugins [[lein-cljsbuild "0.3.2"]]

  ;; cljsbuild options configuration
  :cljsbuild {:builds
               [{:id "funcy-debug"
                 :source-paths ["src"]
                 :compiler {:output-to "resources/public/js/funcy-debug.js"
                            :optimizations :simple
                            :static-fns true}}
                {:id "funcy-release"
                 :source-paths ["src"]
                 :compiler {:output-to "resources/public/js/funcy-release.js"
                            :optimizations :advanced}}]
              
              :repl-listen-port 9001})
