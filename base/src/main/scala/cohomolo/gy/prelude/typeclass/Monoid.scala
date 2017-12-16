package cohomolo.gy
package prelude
package typeclass

import cohomolo.gy.prelude.instances.MonoidInstances

trait Monoid[A] {
  def semigroup: Semigroup[A]
  def empty: A
}

object Monoid extends MonoidInstances {
  def apply[A](implicit A: Monoid[A]): Monoid[A] = A
}
