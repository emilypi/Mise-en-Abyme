package cohomolo.gy.prelude

import scala.language.implicitConversions
import scala.language.experimental.macros
import cohomolo.gy.meta

trait FunctorSyntax {
  implicit def functorOps[F[_], A](fa: F[A])(
      implicit F: Functor[F]): FunctorSyntax.Ops[F, A] =
    new FunctorSyntax.Ops(fa)
}

object FunctorSyntax {
  class Ops[F[_], A](self: F[A])(implicit F: Functor[F]) {
    def map[B](f: A => B): F[B] = macro meta.Ops.f_1
    def void: F[Unit] = F.map[A, Unit](self)(_ => ())
  }
}
