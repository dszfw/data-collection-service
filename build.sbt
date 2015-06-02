name := """data-collection-service"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

libraryDependencies ++= Seq(
  javaJpa,
  "org.hibernate" % "hibernate-entitymanager" % "4.3.7.Final"
)     

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.34"

libraryDependencies ++= Seq(
  "com.adrianhurt" %% "play-bootstrap3" % "0.4.2"
)

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator


fork in run := true