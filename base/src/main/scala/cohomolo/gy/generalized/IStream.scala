package cohomolo.gy
package generalized

sealed abstract class IStreamModule {
  type IStream[A]

  def scons[A](a: => A)(as: => IStream[A]): IStream[A]
  def uncons[A](as: IStream[A]): A
}

private[generalized] object IStreamImpl extends IStreamModule {
  type IStream[A] = Cofree[(() => A, ?), ()]

  def scons[A](a: => A)(as: => IStream[A]): IStream[A] =
    Cofree.wrapCofree[(() => A, ?), ()](())((() => a, as))

  def uncons[A](as: Cofree[(() => A, ?), ()]): A =
    Cofree.runCofree(as)._1()
}
