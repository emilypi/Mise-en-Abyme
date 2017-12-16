package cohomolo.gy
package generalized

import cohomolo.gy.prelude.leibniz.{Identity, ~>}

trait MuModule {
  type Mu[F[_]]
  /* functions */

  def mu[F[_]](f: Algebra[F, ?] ~> Identity): Mu[F]
  //  def subst[G[_[_[_]]]](g: G[λ[α[_] => α[generalized.Mu[α]]]]): G[Mu]
  //  def liftLiskov[F[_], G[_]](ev: ∀[λ[α => F[α] <~< G[α]]])(implicit F: IsCovariant[F]): Mu[F] <~< Mu[G]
}

private[generalized] object MuImpl extends MuModule {
  type Mu[F[_]] = Algebra[F, ?] ~> Identity

  def mu[F[_]](f: Algebra[F, ?] ~> Identity): Mu[F] = f

  //TODO figure out subst for this
  //  def subst[G[_[_[_]]]](g: G[λ[α[_] => α[generalized.Mu[α]]]]): G[Mu] = g
  //  def liftLiskov[F[_], G[_]](ev: ∀[λ[α => F[α] <~< G[α]]])(implicit F: IsCovariant[F]): Mu[F] <~< Mu[G] =
  //    As.unsafeForce
}
