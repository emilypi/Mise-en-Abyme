package cohomolo.gy.prelude

trait ApplyClass[F[_]] extends Apply[F] with FunctorClass[F] {
  implicit final def apply: Apply[F] = this
}
