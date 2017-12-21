package cohomolo.gy
package generalized

import cohomolo.gy.prelude.control.{Coinductive, Inductive, Inf}
import cohomolo.gy.prelude.typeclass.{Cobind, Comonad, Functor}

sealed abstract class IStream[A]
final case class ICons[A](a: Inductive[A], t: Coinductive[IStream[A]])
    extends IStream[A] {
  def extract: A = a.value
}
sealed abstract case class IEmpty[A]() extends IStream[A]

object IStream {

  def scons[A](a: => A, as: => IStream[A]): IStream[A] =
    ICons(Inductive.apply(a), Coinductive.apply(Inf(as)))

  def foldl[A, B](as: => IStream[A], z: => B)(f: (=> B) => A => B): B =
    as match {
      case ICons(a, t) =>
        foldl(t.force, f(z)(a.value))(f)
      case IEmpty() => z
    }

  def #:::[A](as: IStream[A])(bs: IStream[A]): IStream[A] =
    foldl(bs, new IEmpty[A]() {}: IStream[A])(acc => a => scons(a, acc))
}
