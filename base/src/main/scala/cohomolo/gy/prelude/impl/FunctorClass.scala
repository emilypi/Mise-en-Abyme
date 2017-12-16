package cohomolo.gy.prelude.impl

import cohomolo.gy.prelude.typeclass.Functor

trait FunctorClass[F[_]] extends Functor[F] {
  final def functor: Functor[F] = this
}
