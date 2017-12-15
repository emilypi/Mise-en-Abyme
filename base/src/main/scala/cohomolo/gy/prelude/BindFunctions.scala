package cohomolo.gy.prelude

trait BindFunctions {
  def bind[M[_], A, B](ma: M[A])(f: A => M[B])(implicit M: Bind[M]): M[B] =
    M.bind(ma)(f)
}
