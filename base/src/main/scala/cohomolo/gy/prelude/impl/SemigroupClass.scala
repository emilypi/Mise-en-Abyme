package cohomolo.gy.prelude.impl

import cohomolo.gy.prelude.typeclasses.Semigroup

trait SemigroupClass[A] extends Semigroup[A] {
  final def semigroup: Semigroup[A] = this
}
