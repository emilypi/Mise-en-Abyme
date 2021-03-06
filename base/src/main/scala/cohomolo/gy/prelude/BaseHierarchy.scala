package cohomolo.gy.prelude

import cohomolo.gy.prelude.typeclass._

class BaseHierarchy extends BaseHierarchy.BH0

object BaseHierarchy {

  trait BH0 extends BH1 {
    implicit def monadBind[M[_]](implicit M: Monad[M]): Bind[M] = M.bind
    implicit def monadApplicative[M[_]](implicit M: Monad[M]): Applicative[M] =
      M.applicative
    implicit def monadApply[M[_]](implicit M: Monad[M]): Apply[M] =
      M.applicative.apply
    implicit def monadFunctor[M[_]](implicit M: Monad[M]): Functor[M] =
      M.applicative.apply.functor
    implicit def monoidSemigroup[A](implicit A: Monoid[A]): Semigroup[A] =
      A.semigroup
    implicit def comonadCobind[F[_]](implicit F: Comonad[F]): Cobind[F] =
      F.cobind
  }

  trait BH1 extends BH2 {
    implicit def bindApply[M[_]](implicit M: Bind[M]): Apply[M] = M.apply
    implicit def bindFunctor[M[_]](implicit M: Bind[M]): Functor[M] =
      M.apply.functor
  }

  trait BH2 extends BH3 {
    implicit def applicativeApply[M[_]](implicit M: Applicative[M]): Apply[M] =
      M.apply
    implicit def applicativeFunctor[M[_]](
      implicit M: Applicative[M]
    ): Functor[M] = M.apply.functor
  }

  trait BH3 {
    implicit def applyFunctor[M[_]](implicit M: Apply[M]): Functor[M] =
      M.functor
  }
}
