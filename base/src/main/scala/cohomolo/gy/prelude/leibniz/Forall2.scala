package cohomolo.gy

package prelude

package leibniz

import scala.language.implicitConversions

trait Forall2Module {
  type Forall2[F[_, _]]

  type ∀∀[F[_, _]] = Forall2[F]

  trait Prototype[F[_, _]] {
    def apply[A, B]: F[A, B]
    final def make: ∀∀[F] = from(this)
  }

  def specialize[F[_, _], A, B](f: ∀∀[F]): F[A, B]

  def from[F[_, _]](p: Prototype[F]): ∀∀[F]

  def of[F[_, _]]: MkForall2[F]

  def mk[X](implicit u: Unapply[X]): MkForall2[u.F] = of[u.F]

  sealed trait MkForall2[F[_, _]] extends Any {
    type T
    type U
    def from(ft: F[T, U]): ∀∀[F]
    def apply(ft: F[T, U]): ∀∀[F] = from(ft)
  }

  trait Unapply[X] {
    type F[_, _]
  }

  object Unapply {
    implicit def unapply[G[_, _]]: Unapply[∀∀[G]] { type F[A, B] = G[A, B] } =
      new Unapply[∀∀[G]] { type F[A, B] = G[A, B] }
  }
}

trait Forall2Syntax {
  import Forall2Syntax._

  implicit def toForall2Ops[F[_, _]](a: ∀∀[F]): Ops[F] = new Ops[F](a)
}

object Forall2Syntax {
  final class Ops[F[_, _]](val a: ∀∀[F]) extends AnyVal {
    def of[A, B]: F[A, B] = Forall2.specialize(a)
    def apply[A, B]: F[A, B] = of[A, B]
  }
}

private[leibniz] object Forall2Impl extends Forall2Module with Forall2Syntax {
  type Forall2[F[_, _]] = F[Any, Any]

  def from[F[_, _]](p: Prototype[F]): ∀∀[F] = p[Any, Any]

  def specialize[F[_, _], A, B](f: ∀∀[F]): F[A, B] = f.asInstanceOf[F[A, B]]

  def of[F[_, _]]: MkForall2[F] = new MkForall2Impl[F]
}

private[leibniz] final class MkForall2Impl[F[_, _]](val dummy: Boolean = false)
    extends AnyVal
    with Forall2Impl.MkForall2[F] {
  type T = Any
  type U = Any
  def from(ft: F[T, U]): Forall2Impl.∀∀[F] = ft
}
