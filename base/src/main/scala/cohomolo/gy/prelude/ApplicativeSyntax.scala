package cohomolo.gy.prelude

import com.github.ghik.silencer.silent

import language.implicitConversions
import language.experimental.macros
import cohomolo.gy.meta

trait ApplicativeSyntax {
  implicit def applicativeOpsA[A](a: A): ApplicativeSyntax.OpsA[A] =
    new ApplicativeSyntax.OpsA(a)
}

object ApplicativeSyntax {
  class OpsA[A](@silent a: A) {
    def pure[F[_]: Applicative]: F[A] = macro meta.IdOps.id_1
  }
}
