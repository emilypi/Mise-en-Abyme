package cohomolo.gy

package prelude

package syntax

import cohomolo.gy.prelude.typeclass.Apply

import scala.language.experimental.macros
import scala.language.implicitConversions

trait ApplySyntax {
  implicit def applyOps[F[_], A](
    fa: F[A]
  )(implicit F: Apply[F]): ApplySyntax.Ops[F, A] =
    new ApplySyntax.Ops(fa)
}

object ApplySyntax {

  class Ops[F[_]: Apply, A](fa: F[A]) {
    def ap[B](f: F[A => B]): F[B] = macro meta.Ops.f_1
  }
}
