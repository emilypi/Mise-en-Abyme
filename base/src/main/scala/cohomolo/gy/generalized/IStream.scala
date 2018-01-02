package cohomolo.gy
package generalized

import cohomolo.gy.prelude.control.{Coinductive, Inf}
import cohomolo.gy.prelude.leibniz.Maybe2

sealed abstract class IStreamModule {
  type IStream[A]

  def empty[A]: IStream[A]
  def scons[A](a: => A)(as: => generalized.IStream[A]): IStream[A]
  def uncons[A](as: IStream[A]): A
  def smap[A, B](as: => IStream[A])(f: A => B)(
      g: generalized.IStream[A] => generalized.IStream[B]): IStream[B]
}

private[generalized] object IStreamImpl extends IStreamModule {
  type IStream[A] = Maybe2[A, Coinductive[generalized.IStream[A]]]

  def empty[A]: IStream[A] =
    Maybe2.empty2[A, Coinductive[generalized.IStream[A]]]

  def scons[A](a: => A)(as: => generalized.IStream[A]): IStream[A] =
    Maybe2.just2(a, Coinductive.apply(Inf.apply(as)))

  def uncons[A](as: IStream[A]): A =
    Maybe2.Just2.unapply[A, Coinductive[generalized.IStream[A]]](as)._1

  def smap[A, B](as: => IStream[A])(f: A => B)(
      g: generalized.IStream[A] => generalized.IStream[B]): IStream[B] = {
    lazy val lr =
      Maybe2.Just2.unapply[A, Coinductive[generalized.IStream[A]]](as)
    Maybe2.just2(f(lr._1), Coinductive.apply(Inf.apply(g(lr._2.force))))
  }
}
