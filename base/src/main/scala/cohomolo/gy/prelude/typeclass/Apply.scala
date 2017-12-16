package cohomolo.gy
package prelude
package typeclass

import cohomolo.gy.prelude.syntax.ApplySyntax

trait Apply[F[_]] {

  def functor: Functor[F]
  def ap[A, B](fa: F[A])(ff: F[A => B]): F[B]
}

object Apply extends ApplySyntax {
  def apply[F[_]](implicit F: Apply[F]): Apply[F] = F
}