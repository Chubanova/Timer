name := "timer-server"

version := "0.1"

scalaVersion := "2.12.10"
val springBootVersion = "2.1.5.RELEASE"

resolvers ++= Seq(
  Resolver.bintrayRepo("naftoligug", "maven"),
  Resolver.sonatypeRepo("snapshots"),
  Resolver.mavenLocal)

libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.0"
libraryDependencies += "io.github.lognet" % "grpc-spring-boot-starter" % "3.3.0"

libraryDependencies ++= Seq(
  "org.scala-lang.modules" %% "scala-java8-compat" % "0.8.0",
  "ch.qos.logback" % "logback-classic" % "1.1.2",
  "org.springframework.boot" % "spring-boot-starter-data-jpa" % springBootVersion,
  "org.springframework.boot" % "spring-boot-starter-web" % springBootVersion,
  "org.springframework.boot" % "spring-boot-starter-logging" % springBootVersion,
  "org.springframework.boot" % "spring-boot-configuration-processor" % springBootVersion
)
