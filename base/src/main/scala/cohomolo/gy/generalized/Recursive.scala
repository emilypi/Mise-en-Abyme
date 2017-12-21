package cohomolo.gy
package generalized

import cohomolo.gy.prelude.typeclass.Functor

trait Recursive[T] extends Based[T] {
  def project(t: T)(implicit BF: Functor[Base]): Base[T]
}

object Recursive {
  type Aux[T, F[_]] = Recursive[T] { type Base[A] = F[A] }
}
