package cohomolo.gy.comonadic

import cohomolo.gy.meta
import scala.language.experimental.macros
import scala.language.implicitConversions

trait YonedaSyntax {
  implicit def YonedaOps[F[_], A](fa: F[A])(
      implicit F: Yoneda[F, A]): YonedaSyntax.Ops[F, A] =
    new YonedaSyntax.Ops(fa)
}

object YonedaSyntax {
  class Ops[F[_], A](fa: F[A])(implicit ev: Yoneda[F, A]) {
    def runYoneda[B](f: A => B): F[B] = macro meta.Ops.f_1
  }
}
