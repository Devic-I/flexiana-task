(defproject word-scramble "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"],
                 [http-kit "2.2.0"],
                 [compojure "1.6.1"]
                 [http-kit "2.3.0"]
                 [ring/ring-defaults "0.3.2"]]
  :main ^:skip-aot word-scramble.core
  :target-path "target/%s"
  :profiles {:system {:plugins [[lein-cljfmt "0.6.1"]]}
             :cljfmt {:file-pattern #"[^\.#]*\.clj?$"}
             :uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
