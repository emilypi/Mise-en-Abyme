package cohomolo.gy

package prelude

package syntax

import cohomolo.gy.prelude.typeclass.Semigroup
import com.github.ghik.silencer.silent

import scala.language.experimental.macros
import scala.language.implicitConversions

trait SemigroupSyntax {
  implicit def equalOpsA[A: Semigroup](a: A): SemigroupSyntax.OpsA[A] =
    new SemigroupSyntax.OpsA(a)
}

object SemigroupSyntax {

  class OpsA[A: Semigroup](@silent a: A) {
    def append(f: =>A): A = macro meta.Ops.fa_1
  }
}
