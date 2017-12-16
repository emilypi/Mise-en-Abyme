package cohomolo.gy.prelude.impl

import cohomolo.gy.prelude.typeclass.Monoid

trait MonoidClass[A] extends Monoid[A] with SemigroupClass[A] {
  final def monoid: Monoid[A] = this
}
