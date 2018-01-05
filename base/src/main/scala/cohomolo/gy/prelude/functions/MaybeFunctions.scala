package cohomolo.gy

package prelude

package functions

trait MaybeFunctions {
  type Maybe[A]

  def empty[A]: Maybe[A]
  def just[A](a: A): Maybe[A]
  def maybe[A, B](n: B)(f: A => B): Maybe[A] => B
  def fromOption[A](oa: Option[A]): Maybe[A]
  def toOption[A](ma: Maybe[A]): Option[A]

  /* extractors */

  object Just {
    def unapply[A](ma: Maybe[A]): Option[A] = toOption(ma)
  }

  object Empty {
    def unapply[A](ma: Maybe[A]): Boolean = toOption(ma).isEmpty
  }
}

/** To be mixed into Prelude. */
trait MaybePrelude extends MaybeFunctions {
  type Maybe[A] = leibniz.Maybe[A]

  override def empty[A] = leibniz.Maybe.empty[A]
  override def just[A](a: A) = leibniz.Maybe.just(a)
  override def maybe[A, B](n: B)(f: A => B) = leibniz.Maybe.maybe(n)(f)
  override def fromOption[A](oa: Option[A]) = leibniz.Maybe.fromOption(oa)
  override def toOption[A](ma: Maybe[A]) = leibniz.Maybe.toOption(ma)
}
