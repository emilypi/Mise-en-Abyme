package cohomolo.gy.prelude.impl

import cohomolo.gy.prelude.typeclasses.Functor

trait FunctorClass[F[_]] extends Functor[F] {
  final def functor: Functor[F] = this
}
