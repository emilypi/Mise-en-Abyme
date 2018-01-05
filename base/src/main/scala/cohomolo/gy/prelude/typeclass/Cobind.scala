package cohomolo.gy

package prelude

package typeclass

import cohomolo.gy.prelude.functions.CobindFunctions
import cohomolo.gy.prelude.syntax.CobindSyntax

trait Cobind[F[_]] {
  def functor: Functor[F]
  def cobind[A, B](fa: F[A])(f: F[A] => B): F[B]
  def cojoin[A](fa: F[A]): F[F[A]]
}

object Cobind extends CobindFunctions with CobindSyntax {
  def apply[F[_]](implicit F: Cobind[F]): Cobind[F] = F
}
