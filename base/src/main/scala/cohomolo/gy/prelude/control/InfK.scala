package cohomolo.gy.prelude.control

sealed abstract class BoolK
final class TrueK[F[_]] private () extends BoolK
final class FalseK[F[_]] private () extends BoolK
object BoolK {
  sealed abstract class ValueK[F[_], B <: BoolK]
  sealed abstract case class TrueKValue[F[_]]() extends ValueK[F, TrueK[F]]
  sealed abstract case class FalseKValue[F[_]]() extends ValueK[F, FalseK[F]]
}

final class InfK[+F[_], A] private (thunk: () => F[A]) {
  lazy val force: F[A] = thunk()
}

object InfK {
  def apply[F[_], A](a: => F[A]): InfK[F, A] = new InfK(() => a)
}

sealed abstract class MaybeInfK[B <: BoolK, A, F[_]] {
  def force: F[A]
}
final case class InductiveK[F[_], A](value: F[A])
    extends MaybeInfK[TrueK[F], A, F] {
  def force: F[A] = value
}
final case class CoinductiveK[F[_], A](thunk: InfK[F, A])
    extends MaybeInfK[FalseK[F], A, F] {
  def force: F[A] = thunk.force
}
object MaybeInfK {
  def inductiveK[F[_], A](a: F[A]): MaybeInfK[TrueK[F], A, F] = InductiveK(a)
  def coinductiveK[F[_], A](a: InfK[F, A]): MaybeInfK[FalseK[F], A, F] =
    CoinductiveK(a)

  // Needs Leibniz' sweet love
//  def delayK[B[_] <: BoolK, A, F[_]](value: => F[A])(implicit B: BoolK.ValueK[F, B[F]]): MaybeInfK[B, A, F] = B match {
//    case BoolK.TrueKValue => InductiveK(value)
//    case BoolK.FalseKValue => CoinductiveK(InfK(value))
//  }
}
