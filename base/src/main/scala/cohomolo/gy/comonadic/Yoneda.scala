package cohomolo.gy.comonadic

trait Yoneda[F[_], A] {

  def runYoneda[B](f: A => B): F[B]
}

object Yoneda extends YonedaSyntax {
  def apply[F[_], A](implicit Y: Yoneda[F, A]): Yoneda[F, A] = Y
}
