package cohomolo.gy.prelude

trait Cobind[W[_]] extends Functor[W] {

  def cobind[A, B](wa: W[A])(e: W[A] => B): W[B]
}
