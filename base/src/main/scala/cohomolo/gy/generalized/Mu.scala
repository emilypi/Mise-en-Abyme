package cohomolo.gy
package generalized

import cohomolo.gy.prelude.leibniz.{Identity, ~>}

trait MuModule {
  type Mu[F[_]]
  /* functions */

  def mu[F[_]](f: Algebra[F, ?] ~> Identity): Mu[F]
}

private[generalized] object MuImpl extends MuModule {
  type Mu[F[_]] = Algebra[F, ?] ~> Identity

  def mu[F[_]](f: Algebra[F, ?] ~> Identity): Mu[F] = f
}
