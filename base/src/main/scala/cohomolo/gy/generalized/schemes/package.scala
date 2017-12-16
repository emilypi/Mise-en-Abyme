package cohomolo.gy
package generalized

import cohomolo.gy.prelude.typeclass.Functor

package object schemes {

  def cata[F[_], A](fix: Fix[F])(φ: Algebra[F, A])(implicit F: Functor[F]): A =
    φ(F.map(Fix.unfix(fix))(cata(_)(φ)))

  def ana[F[_], A](seed: A)(ψ: Coalgebra[F, A])(
      implicit F: Functor[F]): Fix[F] =
    Fix.fix(F.map(ψ(seed))(ana(_)(ψ)))

}
