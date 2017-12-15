package cohomolo.gy.prelude.typeclasses

import cohomolo.gy.prelude.syntax.ApplicativeSyntax

trait Applicative[F[_]] {
  def apply: Apply[F]
  def pure[A](a: A): F[A]
}

object Applicative extends ApplicativeSyntax {
  def apply[F[_]](implicit F: Applicative[F]): Applicative[F] = F
}