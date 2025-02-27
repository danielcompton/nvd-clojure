(defproject nvd-clojure "2.4.0"
  :description "National Vulnerability Database dependency checker"
  :url "https://github.com/rm-hull/nvd-clojure"
  :license {:name "The MIT License (MIT)"
            :url "https://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.11.0"]
                 [clansi "1.0.0"]
                 [org.clojure/data.json "2.4.0"]
                 [org.slf4j/slf4j-simple "2.0.0-alpha1"]
                 [org.owasp/dependency-check-core "7.0.2"]
                 [rm-hull/table "0.7.1"]
                 [trptcolin/versioneer "0.2.0"]
                 ;; Explicitly depend on a certain Jackson, consistently.
                 ;; (See also: https://github.com/jeremylong/DependencyCheck/issues/3441)
                 [com.fasterxml.jackson.core/jackson-databind "2.13.2.2"]
                 [com.fasterxml.jackson.core/jackson-annotations "2.13.2"]
                 [com.fasterxml.jackson.core/jackson-core "2.13.2"]
                 [com.fasterxml.jackson.module/jackson-module-afterburner "2.13.2"]
                 [org.apache.maven.resolver/maven-resolver-transport-http "1.7.3" #_"Fixes a CVE"]
                 [org.apache.maven/maven-core "3.8.5" #_"Fixes a CVE"]
                 [org.eclipse.jetty/jetty-client "11.0.8" #_"Fixes a CVE" :exclusions [org.slf4j/slf4j-api]]
                 [org.apache.maven.resolver/maven-resolver-spi "1.7.3" #_"Satisfies :pedantic?"]
                 [org.apache.maven.resolver/maven-resolver-api "1.7.3" #_"Satisfies :pedantic?"]
                 [org.apache.maven.resolver/maven-resolver-util "1.7.3" #_"Satisfies :pedantic?"]
                 [org.apache.maven.resolver/maven-resolver-impl "1.7.3" #_"Satisfies :pedantic?"]
                 [org.apache.maven/maven-resolver-provider "3.8.5" #_"Satisfies :pedantic?"]
                 [org.codehaus.plexus/plexus-utils "3.4.1" #_"Satisfies :pedantic?"]]
  :scm {:url "git@github.com:rm-hull/nvd-clojure.git"}
  :source-paths ["src"]
  :jar-exclusions [#"(?:^|/).git"]
  :codox {
          :source-paths ["src"]
          :output-path "doc/api"
          :source-uri "https://github.com/rm-hull/nvd-clojure/blob/master/{filepath}#L{line}"}
  :min-lein-version "2.8.1"
  :target-path "target/%s"
  :profiles {:dev {:plugins [[lein-cljfmt "0.7.0"]
                             [lein-codox "0.10.7"]
                             [lein-cloverage "1.2.3"]
                             [lein-ancient "0.7.0"]
                             [jonase/eastwood "1.2.3"]]
                   :eastwood {:add-linters [:boxed-math
                                            :performance]}
                   :dependencies [[clj-kondo "2022.03.09"]
                                  [commons-collections "20040616"]]}
             :ci {:pedantic? :abort}
             :clj-kondo {:dependencies [[clj-kondo "2022.03.09"]]}
             :skip-self-check {:jvm-opts ["-Dnvd-clojure.internal.skip-self-check=true"
                                          "-Dclojure.main.report=stderr"]}}
  :deploy-repositories [["clojars" {:url "https://clojars.org/repo"
                                    :username :env/clojars_username
                                    :password :env/clojars_password
                                    :sign-releases false}]])
