package cohomolo.gy
package generalized

import cohomolo.gy.prelude.typeclass.Functor

trait Corecursive[T] extends Based[T] {
  def embed(t: Base[T])(implicit BF: Functor[Base]): T
}

object Corecursive {
  type Aux[T, F[_]] = Corecursive[T] { type Base[A] = F[A] }
}
