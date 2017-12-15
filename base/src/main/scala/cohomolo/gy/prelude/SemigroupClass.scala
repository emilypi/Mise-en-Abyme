package cohomolo.gy.prelude

trait SemigroupClass[A] extends Semigroup[A] {
  final def semigroup: Semigroup[A] = this
}
