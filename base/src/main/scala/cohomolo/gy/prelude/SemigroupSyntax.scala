package cohomolo.gy.prelude

import com.github.ghik.silencer.silent

import scala.language.implicitConversions
import scala.language.experimental.macros
import cohomolo.gy.meta

trait SemigroupSyntax {
  implicit def equalOpsA[A: Semigroup](a: A): SemigroupSyntax.OpsA[A] =
    new SemigroupSyntax.OpsA(a)
}

object SemigroupSyntax {
  class OpsA[A: Semigroup](@silent a: A) {
    def append(f: => A): A = macro meta.Ops.fa_1
  }
}
