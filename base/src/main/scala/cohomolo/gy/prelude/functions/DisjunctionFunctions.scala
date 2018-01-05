package cohomolo.gy

package prelude

package functions

import leibniz.Disjunction.{ \/, \/-, -\/ }

trait DisjunctionFunctions {
  @inline def left[L, R](value: L): leibniz.Disjunction[L, R] = -\/(value)
  @inline def right[L, R](value: R): leibniz.Disjunction[L, R] = \/-(value)

  def either[A, B, C](ac: A => C)(bc: B => C): A \/ B => C = _ match {
    case -\/(l) => ac(l)
    case \/-(r) => bc(r)
  }
}
