package cohomolo.gy

package structured

import cohomolo.gy.prelude.typeclass.{ Applicative, Functor, Monad }

trait Traversable[F[_]] extends Functor[F] with Foldable[F] {

  def traverse[G[_]: Applicative, A, B](fga: F[A])(k: A => G[B]): G[F[B]]

  def sequenceA[G[_]: Applicative, A](fga: F[G[A]]): G[F[A]] =
    traverse(fga)(identity)

  def sequence[G[_]: Monad, A](fga: F[G[A]]): G[F[A]]

  def mapM[G[_]: Monad, A, B](fa: F[A])(k: A => G[B]): G[F[B]]
}
