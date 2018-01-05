package cohomolo.gy

package generalized

import cohomolo.gy.prelude.leibniz._
import cohomolo.gy.prelude.typeclass.{ Functor, Monad }
import cohomolo.gy.structured.Traversable

package object schemes {

  def cata[F[_], A](φ: Algebra[F, A])(fix: Fix[F])(implicit F: Functor[F]): A =
    φ(F.map(Fix.unfix(fix))(cata(φ)))

  def ana[F[_], A](
    seed: A
  )(ψ: Coalgebra[F, A])(implicit F: Functor[F]): Fix[F] =
    Fix.fix(F.map(ψ(seed))(ana(_)(ψ)))

  def hylo[F[_]: Functor, A, B](
    φ: Algebra[F, B]
  )(seed: A)(ψ: Coalgebra[F, A]): B =
    cata(φ)(ana(seed)(ψ))

  def meta[F[_]: Functor, A](
    ψ: Coalgebra[F, A]
  )(φ: Algebra[F, A])(fix: Fix[F]): Fix[F] =
    ana(cata(φ)(fix))(ψ)

  def prepro[F[_]: Functor, G[_]: Functor, A](
    α: F ~> G
  )(φ: Algebra[G, A])(fix: Fix[F]): A =
    cata[F, A](f => φ(Forall.specialize[λ[γ => F[γ] => G[γ]], A](α)(f)))(fix)

  def postpro[F[_]: Functor, A](
    seed: A
  )(α: F ~> F)(ψ: Coalgebra[F, A]): Fix[F] =
    ana(seed)(s => Forall.specialize[λ[γ => F[γ] => F[γ]], A](α)(ψ(s)))

  def cataM[F[_], M[_], A](
    φ: AlgebraM[M, F, A]
  )(fix: Fix[F])(implicit T: Traversable[F], M: Monad[M]): M[A] =
    M.bind.bind(T.mapM(Fix.unfix(fix))(cataM(φ)))(φ)

  //anaM :: (Traversable f, Monad m) => (a -> m (f a)) -> (a -> m (Fix f))
  //liftM :: Monad m => (a1 -> r) -> m a1 -> m r
//  def anaM[F[_], M[_], A](seed: A)(ψ: CoalgebraM[M, F, A])(implicit T: Traversable[F], M: Monad[M]): M[Fix[F]] =
//    M.bind.bind(ψ(seed))(M.liftM[M[F[A]], M[Fix[F]]](anaM(seed)(ψ))(Fix.fix(T.mapM(anaM(seed)(ψ)))))

}
