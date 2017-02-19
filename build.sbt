name := """play-java-assessment"""
organization := "nl.iflavours"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.8"

libraryDependencies += filters

libraryDependencies ++= Seq(
  filters,
  javaJpa,
  "org.hibernate" % "hibernate-entitymanager" % "4.3.7.Final",
  "org.mariadb.jdbc" % "mariadb-java-client" % "1.5.5",
  "org.dbunit" % "dbunit" % "2.4.9",
  cache,
  javaWs,
  "org.mindrot" % "jbcrypt" % "0.3m",
  "org.codehaus.jackson" % "jackson-mapper-asl" % "1.8.5"
)

routesGenerator := InjectedRoutesGenerator

fork in run := false


