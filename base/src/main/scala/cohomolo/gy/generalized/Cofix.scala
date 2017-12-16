package cohomolo.gy
package generalized

trait CofixModule {
  type Cofix[F[_]]

  /* functions */

  def unfix[F[_]](f: Cofix[F]): F[generalized.Cofix[F]]
  def subst[G[_[_[_]]]](g: G[λ[α[_] => α[generalized.Cofix[α]]]]): G[Cofix]
  //  def liftLiskov[F[_], G[_]](ev: ∀[λ[α => F[α] <~< G[α]]])(implicit F: IsCovariant[F]): Fix[F] <~< Fix[G]
}

private[generalized] object CofixImpl extends CofixModule {
  type Cofix[F[_]] = F[generalized.Cofix[F]]

  def unfix[F[_]](f: Cofix[F]): F[generalized.Cofix[F]] = f
  def subst[G[_[_[_]]]](g: G[λ[α[_] => α[generalized.Cofix[α]]]]): G[Cofix] = g

  //  def liftLiskov[F[_], G[_]](ev: ∀[λ[α => F[α] <~< G[α]]])(implicit F: IsCovariant[F]): Fix[F] <~< Fix[G] =
  //    As.unsafeForce
}
