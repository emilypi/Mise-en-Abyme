package cohomolo.gy
package generalized

import cohomolo.gy.prelude.control.Inf
import cohomolo.gy.prelude.leibniz.Maybe2

sealed abstract class IStreamFunctions[A] {
  type IStream[A]

  def empty[A]: IStream[A]
  def scons[A](a: => A, as: => IStream[A]): IStream[A]
  def foldl[A, B](as: => IStream[A], z: => B)(f: (=> B) => A => B): B
  def sappend[A](as: IStream[A])(bs: IStream[A]): IStream[A]

}

//private[generalized] object IStream extends IStreamFunctions {
//  type IStream[A] = generalized.Cofree[(A, ?), A]
//
//  override def empty[A]: Cofree[Tuple2[A, _], A] = ???
//
//  override def scons[A](a: => A, as: => IStream[A]): IStream[A] = ???
//
//  override def foldl[A, B](as: => Cofree[Tuple2[A, _], A], z: => B)(f: B => A => B): B = ???
//
//  override def sappend[A](as: Cofree[Tuple2[A, _], A])(bs: Cofree[Tuple2[A, _], A]): Cofree[Tuple2[A, _], A] = ???
//}
