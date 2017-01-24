
import Dependencies._

lazy val root = (project in file(".")).settings(

  inThisBuild(List(
    organization := "edu.litlab",
    scalaVersion := "2.12.1",
    version      := "0.1.0-SNAPSHOT"
  )),

  name := "Litcene",

  libraryDependencies += scalaTest % Test,
  libraryDependencies += commonsIo

)
