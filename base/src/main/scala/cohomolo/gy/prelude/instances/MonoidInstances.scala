package cohomolo.gy.prelude.instances

import cohomolo.gy.prelude.impl.MonoidClass
import cohomolo.gy.prelude.typeclass.Monoid

trait MonoidInstances {

  implicit val string: Monoid[String] = new MonoidClass[String] {
    def append(a1: String, a2: =>String) = a1 + a2
    def empty = ""
  }

}
