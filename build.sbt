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
  resolvers += Resolver.sonatypeRepo("releases"),
)

lazy val root = (project in file("."))
  .settings(name := "mise-en-abyme")
  .aggregate(base, test, meta)

lazy val base = project
  .settings(moduleName := "base")
  .settings(name := "base")
  .settings(addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.4"))
  .settings(libraryDependencies += "com.github.ghik" %% "silencer-lib" % "0.5")
  .settings(standardFeatures ++ standardSettings ++ publishSettings: _*)
  .dependsOn(meta)

lazy val test = project
  .settings(moduleName := "test")
  .settings(addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.4"))
  .settings(standardSettings ++ publishSettings: _*)
  .dependsOn(base)

lazy val meta = project
  .settings(moduleName := "base")
  .settings(addCompilerPlugin("com.github.ghik" %% "silencer-plugin" % "0.5"))
  .settings(standardSettings ++ publishSettings: _*)
  .settings(
    libraryDependencies ++= List(
      "org.scala-lang" % "scala-reflect" % scalaVersion.value,
      "org.scala-lang" % "scala-compiler" % scalaVersion.value % "provided",
      "com.github.ghik" %% "silencer-lib" % "0.5"
    )
  )

lazy val standardFeatures = scalacOptions ++= List(
  "-deprecation", // Emit warning and location for usages of deprecated APIs.
  "-encoding",
  "utf-8", // Specify character encoding used by source files.
  "-explaintypes", // Explain type errors in more detail.
  "-feature", // Emit warning and location for usages of features that should be imported explicitly.
  "-deprecation",
  "-Xfuture",
  "-Xsource:2.12",
  "-language:implicitConversions",
  "-language:higherKinds",
  "-language:existentials",
  "-language:postfixOps",
  "-unchecked", // Enable additional warnings where generated code depends on assumptions.
  "-Xcheckinit", // Wrap field accessors to throw an exception on uninitialized access.
  "-Xlint:adapted-args", // Warn if an argument list is modified to match the receiver.
  "-Xlint:constant", // Evaluation of a constant arithmetic expression results in an error.
  "-Xlint:doc-detached", // A Scaladoc comment appears to be detached from its element.
  "-Xlint:inaccessible", // Warn about inaccessible types in method signatures.
  "-Xlint:infer-any", // Warn when a type argument is inferred to be `Any`.
  "-Xlint:nullary-override", // Warn when non-nullary `def f()' overrides nullary `def f'.
  "-Xlint:nullary-unit", // Warn when nullary methods return Unit.
  "-Xlint:poly-implicit-overload", // Parameterized overloaded implicit methods are not visible as view bounds.
  "-Xlint:stars-align", // Pattern sequence wildcard must align with sequence component.
  "-Xlint:type-parameter-shadow", // A local type parameter shadows a type already in scope.
  "-Xlint:unsound-match", // Pattern match may not be typesafe.
  "-Yno-adapted-args", // Do not adapt an argument list (either by inserting () or creating a tuple) to match the receiver.
  "-Ypartial-unification", // Enable partial unification in type constructor inference
  "-Ywarn-dead-code", // Warn when dead code is identified.
  "-Ywarn-extra-implicit", // Warn when more than one implicit parameter section is defined.
  "-Ywarn-inaccessible", // Warn about inaccessible types in method signatures.
  "-Ywarn-infer-any", // Warn when a type argument is inferred to be `Any`.
  "-Ywarn-nullary-override", // Warn when non-nullary `def f()' overrides nullary `def f'.
  "-Ywarn-nullary-unit", // Warn when nullary methods return Unit.
  "-Ywarn-numeric-widen", // Warn when numerics are widened.
  "-Ywarn-unused:implicits", // Warn if an implicit parameter is unused.
  "-Ywarn-unused:params", // Warn if a value parameter is unused.
  "-Ywarn-unused:patvars", // Warn if a variable bound in a pattern is unused.
  "-Ywarn-value-discard"
)
