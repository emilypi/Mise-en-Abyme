package cohomolo.gy.prelude

trait MonoidInstances {

  implicit val string: Monoid[String] = new MonoidClass[String] {
    def append(a1: String, a2: => String) = a1 + a2
    def empty = ""
  }

}
