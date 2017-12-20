package cohomolo.gy
package comonadic

trait Coyoneda[F[_], A] {
  type B

  def f: B => A
  def fb: F[B]
}

object Coyoneda extends CoyonedaSyntax {
  def apply[F[_], A](implicit Y: Coyoneda[F, A]): Coyoneda[F, A] = Y
}
