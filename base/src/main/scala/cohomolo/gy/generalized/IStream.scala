package cohomolo.gy

package generalized

import cohomolo.gy.prelude.typeclass.{ Cobind, Comonad, Functor }

sealed abstract class IStreamModule {
  type IStream[A]

  def scons[A](a: =>A)(as: =>IStream[A]): IStream[A]
  def uncons[A](as: =>IStream[A]): (() => A, IStream[A])
  def iterate[A](a: =>A)(f: A => A): IStream[A]
}

private[generalized] object IStreamImpl extends IStreamModule { self =>
  type IStream[A] = Cofree[(() => A, ?), Unit]

  def scons[A](a: =>A)(as: =>IStream[A]): IStream[A] =
    Cofree.wrapCofree[(() => A, ?), Unit](())((() => a, as))

  def uncons[A](as: =>IStream[A]): (() => A, IStream[A]) =
    Cofree.runCofree[(() => A, ?), Unit](as)

  def iterate[A](a: =>A)(f: A => A): IStream[A] =
    scons(a)(iterate(f(a))(f))

  def functor: Functor[IStream] = new Functor[IStream] {
    override def map[A, B](fa: IStream[A])(f: A => B): IStream[B] = {
      lazy val uncons: (() => A, IStream[A]) =
        Cofree.runCofree[(() => A, ?), Unit](fa)
      scons(f(uncons._1()))(map[A, B](uncons._2)(f))
    }
  }

  def comonad: Comonad[IStream] = new Comonad[IStream] {
    override def cobind: Cobind[IStream] = new Cobind[IStream] {

      def cojoin[A](fa: IStream[A]): IStream[IStream[A]] =
        functor.map[A, IStream[A]](fa)(iterate(_)(identity))

      def functor: Functor[IStream] = self.functor

      override def cobind[A, B](
        fa: IStream[A]
      )(f: IStream[A] => B): IStream[B] =
        iterate(f(fa))(identity)
    }

    def copoint[A](fa: IStream[A]): A =
      uncons[A](fa)._1()
  }

}
