package cohomolo.gy.prelude

trait Applicative[F[_]] {

  def apply: Apply[F]

  def pure[A](a: A): F[A]

}
