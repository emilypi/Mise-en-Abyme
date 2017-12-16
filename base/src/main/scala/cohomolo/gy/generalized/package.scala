package cohomolo.gy

import cohomolo.gy.prelude.typeclass.Functor

package object generalized {

  type Algebra[F[_], A] = F[A] => A
  type AlgebraM[M[_], F[_], A] = F[A] => M[A]
  type Coalgebra[F[_], A] = A => F[A]
  type CoalgebraM[M[_], F[_], A] = A => M[F[A]]

  val Fix: FixModule = FixImpl
  type Fix[F[_]] = Fix.Fix[F]

  val Mu: MuModule = MuImpl
  type Mu[F[_]] = Mu.Mu[F]

  val Cofix: CofixModule = CofixImpl
  type Cofix[F[_]] = Cofix.Cofix[F]
}
