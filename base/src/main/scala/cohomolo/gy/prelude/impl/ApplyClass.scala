package cohomolo.gy.prelude.impl

import cohomolo.gy.prelude.typeclasses.Apply

trait ApplyClass[F[_]] extends Apply[F] with FunctorClass[F] {
  implicit final def apply: Apply[F] = this
}
