package cohomolo.gy

import cohomolo.gy.generalized.Cofree

package object generalized {

  type Algebra[F[_], A] = F[A] => A
  type AlgebraM[M[_], F[_], A] = F[A] => M[A]
  type Coalgebra[F[_], A] = A => F[A]
  type CoalgebraM[M[_], F[_], A] = A => M[F[A]]

  val Fix: FixModule = FixImpl
  type Fix[F[_]] = Fix.Fix[F]

  val Cofree: CofreeFunctions = CofreeImpl
  type Cofree[F[_], A] = Cofree.Cofree[F, A]

  val Free: FreeModule = FreeImpl
  type Free[F[_], A] = Free.Free[F, A]

}
