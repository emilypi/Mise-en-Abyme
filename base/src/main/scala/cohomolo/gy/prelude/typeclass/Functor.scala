package cohomolo.gy
package prelude
package typeclass

import cohomolo.gy.prelude.functions.FunctorFunctions
import cohomolo.gy.prelude.syntax.FunctorSyntax

trait Functor[F[_]] {

  def map[A, B](fa: F[A])(f: A => B): F[B]
}

object Functor extends FunctorFunctions with FunctorSyntax {
  def apply[F[_]](implicit F: Functor[F]): Functor[F] = F
}
