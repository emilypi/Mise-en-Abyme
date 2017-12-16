package cohomolo.gy.prelude.impl

import cohomolo.gy.prelude.typeclass.Semigroup

trait SemigroupClass[A] extends Semigroup[A] {
  final def semigroup: Semigroup[A] = this
}
