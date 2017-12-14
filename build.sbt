import sbt._
import Keys._

lazy val standardSettings = List(organization := "cohomolo.gy",
                                 scalaVersion := "2.12.4",
                                 version := "0.1.0-SNAPSHOT",
                                 exportJars := true,
                                 resolvers += Resolver.sonatypeRepo("releases"))

lazy val publishSettings = List(
  organizationName := "Cohomolo.gy",
  organizationHomepage := Some(
    url("https://github.com/emilypi/StructuredRecursionSchemes")),
  homepage := Some(url("https://cohomolo.gy/recursion")),
  scmInfo := Some(
    ScmInfo(url("https://github.com/emilypi/StructuredRecursionSchemes"),
            "scm:git@github.org:emilypi/StructuredRecursionSchemes.git"))
)

lazy val root = (project in file("."))
  .settings(name := "recursion-schemes")
  .settings(standardSettings: _*)
  .aggregate(base)
  .dependsOn(base)
  .settings(scalaSource in Compile := baseDirectory.value / "base/shared/src")
  .settings(scalaSource in Test := baseDirectory.value / "test/shared/src")

lazy val base = (project in file("base"))
  .settings(name := "base")
  .settings(standardSettings ++ publishSettings: _*)

lazy val test = (project in file("test"))
  .settings(name := "test")
  .settings(standardSettings ++ publishSettings: _*)
