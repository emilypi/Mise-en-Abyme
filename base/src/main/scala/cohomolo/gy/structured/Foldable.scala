package cohomolo.gy

package structured

import cohomolo.gy.prelude.typeclass.Monoid

trait Foldable[F[_]] {

  def foldr[A, B](fa: F[A])(z: B)(f: A => B => B): B
  def foldMap[A, M: Monoid](fa: F[A])(f: A => M): M
}
