package cohomolo.gy
package generalized

import cohomolo.gy.prelude.control.Inf
import cohomolo.gy.prelude.leibniz.Maybe2
import Maybe2.Just2
import cohomolo.gy.prelude.typeclass.Functor

trait CofreeModule {
  type Cofree[F[_], A]

  def mapCofree[F[_]: Functor, A, B](
      f: A => B,
      g: generalized.Cofree[F, A] => generalized.Cofree[F, B],
      cofree: Cofree[F, A]
  ): Cofree[F, B]

  def runCofree[F[_], A](f: => Cofree[F, A]): Inf[F[generalized.Cofree[F, A]]]
  def <::[F[_], A](a: A, f: => F[generalized.Cofree[F, A]]): Cofree[F, A]
}

private[generalized] object CofreeImpl extends CofreeModule {
  type Cofree[F[_], A] = Maybe2[A, Inf[F[generalized.Cofree[F, A]]]]

  def mapCofree[F[_]: Functor, A, B](
      f: A => B,
      g: generalized.Cofree[F, A] => generalized.Cofree[F, B],
      cofree: Cofree[F, A]
  ): Cofree[F, B] = {
    lazy val ff = Just2.unapply[A, Inf[F[generalized.Cofree[F, A]]]](cofree)
    Maybe2.just2(f(ff._1), Inf.apply(Functor[F].map(ff._2.force)(g)))
  }

  def runCofree[F[_], A](f: => Cofree[F, A]): Inf[F[generalized.Cofree[F, A]]] =
    Just2.unapply[A, Inf[F[generalized.Cofree[F, A]]]](f)._2

  def <::[F[_], A](a: A, f: => F[generalized.Cofree[F, A]]): Cofree[F, A] =
    Maybe2.just2(a, Inf.apply(f))
}
