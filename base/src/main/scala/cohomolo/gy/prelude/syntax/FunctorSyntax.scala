package cohomolo.gy
package prelude
package syntax

import scala.language.experimental.macros
import scala.language.implicitConversions

trait FunctorSyntax {
  implicit def functorOps[F[_], A](fa: F[A])(
      implicit F: typeclass.Functor[F]): FunctorSyntax.Ops[F, A] =
    new FunctorSyntax.Ops(fa)
}

object FunctorSyntax {
  class Ops[F[_], A](self: F[A])(implicit F: typeclass.Functor[F]) {
    def map[B](f: A => B): F[B] = macro meta.Ops.f_1
    def void: F[Unit] = F.map[A, Unit](self)(_ => ())
  }
}
