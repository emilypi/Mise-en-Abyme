package cohomolo.gy.prelude.syntax

import cohomolo.gy.meta
import cohomolo.gy.prelude.typeclasses.Show
import com.github.ghik.silencer.silent

import scala.language.experimental.macros
import scala.language.implicitConversions

trait ShowSyntax {
  implicit def showOps[A](self: A)(implicit A: Show[A]) =
    new ShowSyntax.Ops[A](self)(A)
}

object ShowSyntax {
  class Ops[A](@silent self: A)(implicit @silent A: Show[A]) {
    def show: String = macro meta.Ops.f_0
  }
}
