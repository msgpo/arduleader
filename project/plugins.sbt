addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "2.1.1")

resolvers += Resolver.url("artifactory", url("http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases"))(Resolver.ivyStylePatterns)

//addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.9.1")
//addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "latest.integration")

addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.7.0")

addSbtPlugin("org.scala-sbt" % "sbt-android" % "0.6.4-SNAPSHOT")

addSbtPlugin("com.github.sbt" %% "sbt-scalabuff" % "1.3.7")
