package cohomolo.gy

package generalized

sealed abstract class IStreamModule {
  type IStream[A]

  def scons[A](a: =>A)(as: =>IStream[A]): IStream[A]
  def uncons[A](as: =>IStream[A]): (A, IStream[A])
}

private[generalized] object IStreamImpl extends IStreamModule { self =>
  type IStream[A] = Cofree[(A, ?), Unit]

  def scons[A](a: =>A)(as: =>IStream[A]): IStream[A] =
    Cofree.wrapCofree[(A, ?), Unit](())((a, as))

  def uncons[A](as: =>IStream[A]): (A, IStream[A]) =
    Cofree.runCofree[(A, ?), Unit](as)
}
