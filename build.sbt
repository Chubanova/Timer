name := "timer"

version := "0.1"

scalaVersion := "2.13.2"

lazy val server = (project in file("timer-server"))

lazy val proto = (project in file("timer-proto"))