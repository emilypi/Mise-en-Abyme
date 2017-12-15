package cohomolo.gy.prelude

trait Apply[F[_]] {

  def functor: Functor[F]

  def ap[A, B](fa: F[A])(ff: F[A => B]): F[B]
}
