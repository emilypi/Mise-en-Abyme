package cohomolo.gy.prelude.functions

import cohomolo.gy.prelude.typeclasses.Cobind

trait CobindFunctions {
  def cobind[W[_], A, B](wa: W[A])(f: W[A] => B)(implicit W: Cobind[W]): W[B] =
    W.cobind(wa)(f)
}
