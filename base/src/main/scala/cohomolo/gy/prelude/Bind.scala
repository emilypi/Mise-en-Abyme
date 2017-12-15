package cohomolo.gy.prelude

trait Bind[M[_]] {

  def bind[A, B](ma: M[A])(k: A => M[B]): M[B]

}
