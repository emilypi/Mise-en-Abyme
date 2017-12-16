package cohomolo.gy
package generalized

import prelude.leibniz.Maybe2

trait CofreeModule {
  type Cofree[F[_], A]

  def runCofree[F[_], A](as: Cofree[F, A]): Maybe2[A, F[Cofree[F, A]]]
}

//private[generalized] object CofreeImpl extends CofreeModule {
//  type Cofree[F[_], A] = Maybe2[A, F[generalized.Cofree[F, A]]]
//
//  def runCofree[F[_], A](f: Cofree[F, A]): Maybe2[A, F[generalized.Cofree[F, A]]] = f
//}
