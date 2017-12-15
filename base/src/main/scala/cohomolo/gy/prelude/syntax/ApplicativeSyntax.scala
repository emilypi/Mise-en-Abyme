package cohomolo.gy.prelude.syntax

import cohomolo.gy.meta
import cohomolo.gy.prelude.typeclasses.Applicative
import com.github.ghik.silencer.silent

import scala.language.experimental.macros
import scala.language.implicitConversions

trait ApplicativeSyntax {
  implicit def applicativeOpsA[A](a: A): ApplicativeSyntax.OpsA[A] =
    new ApplicativeSyntax.OpsA(a)
}

object ApplicativeSyntax {
  class OpsA[A](@silent a: A) {
    def pure[F[_]: Applicative]: F[A] = macro meta.IdOps.id_1
  }
}
