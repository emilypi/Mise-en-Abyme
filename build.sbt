import sbt._
import Keys._

lazy val publishSettings = List(
  organizationName := "Cohomolo.gy",
  organizationHomepage := Some(
    url("https://github.com/emilypi/StructuredRecursionSchemes")),
  homepage := Some(url("https://cohomolo.gy/recursion")),
  scmInfo := Some(
    ScmInfo(
      url("https://github.com/emilypi/StructuredRecursionSchemes"),
      "scm:git@github.org:emilypi/StructuredRecursionSchemes.git"
    )
  )
)

lazy val standardSettings = List(
  organization := "cohomolo.gy",
  scalaVersion := "2.12.4",
  version := "0.1.0-SNAPSHOT",
  exportJars := true,
  resolvers += Resolver.sonatypeRepo("releases")
)

lazy val root = (project in file("."))
  .settings(name := "recursion-schemes")
  .settings(standardSettings: _*)
  .settings(scalaSource in Compile := baseDirectory.value / "meta/shared/src")
  .settings(scalaSource in Compile := baseDirectory.value / "base/shared/src")
  .settings(scalaSource in Test := baseDirectory.value / "test/shared/src")
  .aggregate(base, test, meta)
  .dependsOn(base, test, meta)

lazy val base = (project in file("base"))
  .settings(name := "base")
  .settings(addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.4"))
  .settings(libraryDependencies += "com.github.ghik" %% "silencer-lib" % "0.5")
  .settings(standardSettings ++ publishSettings: _*)
  .dependsOn(meta)

lazy val test = (project in file("test"))
  .settings(name := "test")
  .settings(addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.4"))
  .settings(standardSettings ++ publishSettings: _*)
  .dependsOn(meta, base)

lazy val meta = (project in file("meta"))
  .settings(name := "meta")
  .settings(addCompilerPlugin("com.github.ghik" %% "silencer-plugin" % "0.5"))
  .settings(standardSettings ++ publishSettings: _*)
  .settings(
    libraryDependencies ++= List(
      "org.scala-lang" % "scala-reflect" % scalaVersion.value,
      "org.scala-lang" % "scala-compiler" % scalaVersion.value % "provided",
      "com.github.ghik" %% "silencer-lib" % "0.5"
    )
  )
