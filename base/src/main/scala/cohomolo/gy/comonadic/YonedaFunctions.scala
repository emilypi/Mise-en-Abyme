package cohomolo.gy.comonadic

object YonedaFunctions {

  def runYoneda[F[_], A, B](f: A => B)(implicit Y: Yoneda[F, A]): F[B] =
    Y.runYoneda(f)
}
