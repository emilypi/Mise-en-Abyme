package cohomolo.gy.structured

package object schemes {

  /**
    * `z` is some default value that can be any finite `A`
    */
  def ackermann[A](as: List[A])(bs: List[A])(z: A): List[A] =
    (as, bs) match {
      case (Nil, bs) => bs
      case (a :: as, Nil) => ackermann(as)(z :: Nil)(z)
      case (a :: as, b :: bs) => ackermann(as)(ackermann(a :: as)(bs)(z))(z)
    }
}
