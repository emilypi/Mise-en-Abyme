package cohomolo.gy

package prelude

package leibniz

import Prelude._
import functions.MaybeFunctions
import syntax.MaybeSyntax
import impl.MonadClass
import typeclass.Show

sealed trait MaybeModule extends MaybeFunctions {

  def fold[A, B](ma: Maybe[A])(f: A => B, b: =>B): B =
    toOption(ma).fold(b)(f)

  /* typeclass instances */

//  def isCovariant: IsCovariant[Maybe]
  def monad: Monad[Maybe]
  def show[A: Show]: Show[Maybe[A]]
}

object MaybeModule extends MaybeSyntax {
  implicit def monadMaybe: Monad[Maybe] = Maybe.monad
//  implicit def isCovariantMaybe: IsCovariant[Maybe] = Maybe.isCovariant
  implicit def showMaybe[A: Show]: Show[Maybe[A]] = Maybe.show[A]
}

private[leibniz] object MaybeImpl extends MaybeModule {
  type Maybe[A] = Option[A]

  def empty[A]: Maybe[A] = None
  def just[A](a: A): Maybe[A] = Some(a)

  def maybe[A, B](n: B)(f: A => B): Maybe[A] => B = _ match {
    case Some(a) => f(a)
    case None => n
  }
  def fromOption[A](oa: Option[A]): Maybe[A] = oa
  def toOption[A](ma: Maybe[A]): Option[A] = ma

//  def isCovariant: IsCovariant[Maybe] = typeclass.IsCovariant.scalaCovariant[Option]
  def monad: Monad[Maybe] = instance

  def show[A](implicit A: Show[A]): Show[Maybe[A]] = {
    case Some(a) => s"Just(${A.show(a)})"
    case None => "Empty"
  }

  private val instance =
    new MonadClass.Template[Maybe] {

      override def ap[A, B](ma: Maybe[A])(mf: Maybe[A => B]): Maybe[B] =
        mf match {
          case Some(f) => ma.map(f)
          case None => None
        }

      override def bind[A, B](ma: Maybe[A])(f: A => Maybe[B]): Maybe[B] =
        ma.flatMap(f)

      override def map[A, B](ma: Maybe[A])(f: A => B): Maybe[B] =
        ma.map(f)

      override def pure[A](a: A): Maybe[A] =
        just(a)
    }
}
