package cohomolo.gy.prelude

final case class Const[A, B](getConst: A) {
  def retag[C]: Const[A, C] = this.asInstanceOf[Const[A, C]]
}

object Const extends ConstInstances
