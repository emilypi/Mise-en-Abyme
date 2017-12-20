package cohomolo.gy
package generalized

import prelude.leibniz.Disjunction.\/

trait FreeModule {
  type Free[F[_], A]

  def runFree[F[_], A](f: Free[F, A]): A \/ F[generalized.Free[F, A]]
}

private[generalized] object FreeImpl extends FreeModule {
  type Free[F[_], A] = A \/ F[generalized.Free[F, A]]

  def runFree[F[_], A](f: Free[F, A]): A \/ F[generalized.Free[F, A]] = f
}
