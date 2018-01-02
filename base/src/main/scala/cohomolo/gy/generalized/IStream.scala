package cohomolo.gy
package generalized

import cohomolo.gy.prelude.typeclass.Functor

sealed abstract class IStreamModule {
  type IStream[A]

  def scons[A](a: => A)(as: => IStream[A]): IStream[A]
  def uncons[A](as: IStream[A]): (() => A, IStream[A])
}

private[generalized] object IStreamImpl extends IStreamModule {
  type IStream[A] = Cofree[(() => A, ?), Unit]

  def scons[A](a: => A)(as: => IStream[A]): IStream[A] =
    Cofree.wrapCofree[(() => A, ?), Unit](())((() => a, as))

  def uncons[A](as: IStream[A]): (() => A, IStream[A]) =
    Cofree.runCofree[(() => A, ?), Unit](as)

  def functor[A]: Functor[IStream] = new Functor[IStream] {
    override def map[A, B](fa: IStream[A])(f: A => B): IStream[B] = {
      lazy val uncons: (() => A, IStream[A]) =
        Cofree.runCofree[(() => A, ?), Unit](fa)
      scons(f(uncons._1()))(map[A, B](uncons._2)(f))
    }
  }
}
