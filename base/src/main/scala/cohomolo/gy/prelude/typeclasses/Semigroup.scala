package cohomolo.gy.prelude.typeclasses

import cohomolo.gy.prelude.syntax.SemigroupSyntax

trait Semigroup[A] {
  def append(a1: A, a2: => A): A
}

object Semigroup extends SemigroupSyntax {
  def apply[A](implicit A: Semigroup[A]): Semigroup[A] = A
}
