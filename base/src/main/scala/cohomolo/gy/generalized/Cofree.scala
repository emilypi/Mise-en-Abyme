package cohomolo.gy
package generalized

import cohomolo.gy.prelude.control.{Coinductive, Inf}

sealed abstract class CofreeFunctions {
  type Cofree[F[_], A]

  def runCofree[F[_], A](f: Cofree[F, A]): F[generalized.Cofree[F, A]]

  def wrapCofree[F[_], A](a: A)(f: => F[generalized.Cofree[F, A]]): Cofree[F, A]
}

private[generalized] object CofreeImpl extends CofreeFunctions {
  type Cofree[F[_], A] = (A, Coinductive[F[generalized.Cofree[F, A]]])

  def runCofree[F[_], A](f: Cofree[F, A]): F[generalized.Cofree[F, A]] =
    f._2.force

  def wrapCofree[F[_], A](a: A)(
      f: => F[generalized.Cofree[F, A]]): Cofree[F, A] =
    (a, Coinductive.apply(Inf.apply(f)))

}
