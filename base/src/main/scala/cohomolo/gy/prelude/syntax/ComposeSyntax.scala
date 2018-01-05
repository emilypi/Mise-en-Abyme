package cohomolo.gy

package prelude

package syntax

import cohomolo.gy.prelude.typeclass.Compose
import com.github.ghik.silencer.silent

import scala.language.experimental.macros
import scala.language.implicitConversions

trait ComposeSyntax {
  implicit def composeOps[=>:[_, _], B, C](
    fa: B =>: C
  )(implicit F: Compose[=>:]): ComposeSyntax.Ops[=>:, B, C] =
    new ComposeSyntax.Ops(fa)
}

object ComposeSyntax {

  class Ops[=>:[_, _]: Compose, B, C](@silent self: B =>: C) {
    def compose[A](f: A =>: B): A =>: C = macro meta.Ops.fa_1
  }
}
