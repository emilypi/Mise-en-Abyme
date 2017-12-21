package cohomolo.gy
package generalized

import cohomolo.gy.prelude.control.Inf
import cohomolo.gy.prelude.typeclass.Functor

trait CofreeModule {
  type Cofree[F[_], A]

  def mapCofree[F[_]: Functor, A, B](
      f: A => B,
      g: Cofree[F, A] => Cofree[F, B],
      cofree: Cofree[F, A]
  ): Cofree[F, B]

  def runCofree[F[_], A](f: => Cofree[F, A]): Inf[F[generalized.Cofree[F, A]]]
  def <::[F[_], A](a: A, f: => F[generalized.Cofree[F, A]]): Cofree[F, A]
}

private[generalized] object CofreeImpl extends CofreeModule {
  type Cofree[F[_], A] = (A, Inf[F[generalized.Cofree[F, A]]])

  def mapCofree[F[_]: Functor, A, B](
      f: A => B,
      g: Cofree[F, A] => Cofree[F, B],
      cofree: Cofree[F, A]
  ): Cofree[F, B] = (f(cofree._1), g(cofree)._2)

  def runCofree[F[_], A](f: => Cofree[F, A]): Inf[F[generalized.Cofree[F, A]]] =
    f._2

  def <::[F[_], A](a: A, f: => F[generalized.Cofree[F, A]]): Cofree[F, A] =
    (a, Inf.apply(f))
}
