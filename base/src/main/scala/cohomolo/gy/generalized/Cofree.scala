package cohomolo.gy
package generalized

trait CofreeModule {
  type Cofree[F[_], A]

  def runCofree[F[_], A](
      f: Cofree[F, A]): F[generalized.Cofree[F, A]]
}

private[generalized] object CofreeImpl extends CofreeModule {
  type Cofree[F[_], A] = (A, F[generalized.Cofree[F, A]])

  def runCofree[F[_], A](
      f: Cofree[F, A]): F[generalized.Cofree[F, A]] = f._2
}
