package cohomolo.gy

import cohomolo.gy.prelude.leibniz.∀

package object comonadic {

  type GAlgebra[F[_], W[_], A] = F[W[A]] => A
  type GAlgebraM[W[_], M[_], F[_], A] = F[W[A]] => M[A]

  type GCoalgebra[F[_], M[_], A] = A => F[M[A]]
  type GCoalgebraM[F[_], M[_], N[_], A] = A => N[F[M[A]]]

  type ElgotAlgebra[W[_], F[_], A] = W[F[A]] => A
  type ElgotAlgebraM[W[_], M[_], F[_], A] = W[F[A]] => M[A]

  type ElgotCoalgebra[E[_], F[_], A] = A => E[F[A]]
  type ElgotCoalgebraM[E[_], M[_], F[_], A] = A => M[E[F[A]]]
}
