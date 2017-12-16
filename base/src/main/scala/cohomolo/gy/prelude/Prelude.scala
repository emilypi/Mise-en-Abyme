package cohomolo.gy
package prelude

import syntax._
import leibniz._
import scala.language.implicitConversions

trait Prelude
    extends functions.DisjunctionFunctions
    with leibniz.ForallSyntax
    with leibniz.Forall2Syntax
    with leibniz.IdentityTypes
//  with leibniz.MaybePrelude
    with functions.BindFunctions
    with functions.FunctorFunctions {

  // Base Class
  // ==========
  type Applicative[F[_]] = typeclass.Applicative[F]
  type Apply[F[_]] = typeclass.Apply[F]
  type Bind[M[_]] = typeclass.Bind[M]
  type Compose[P[_, _]] = typeclass.Compose[P]
  type Functor[F[_]] = typeclass.Functor[F]
  type Monad[M[_]] = typeclass.Monad[M]
  type Category[=>:[_, _]] = typeclass.Category[=>:]
  type Comonad[F[_]] = typeclass.Comonad[F]
  type Cobind[F[_]] = typeclass.Cobind[F]
//  type IsCovariant[F[_]] = typeclass.IsCovariant[F]
//  type IsContravariant[F[_]] = typeclass.IsContravariant[F]
  type Show[A] = typeclass.Show[A]
  type Semigroup[T] = typeclass.Semigroup[T]
  type Monoid[T] = typeclass.Monoid[T]

  def Applicative[F[_]](implicit F: Applicative[F]): Applicative[F] = F
  def Apply[F[_]](implicit F: Apply[F]): Apply[F] = F
  def Bind[F[_]](implicit F: Bind[F]): Bind[F] = F
  def Compose[P[_, _]](implicit P: Compose[P]): Compose[P] = P
  def Functor[F[_]](implicit F: Functor[F]): Functor[F] = F
  def Monad[M[_]](implicit M: Monad[M]): Monad[M] = M
  def Category[=>:[_, _]](implicit P: Category[=>:]): Category[=>:] = P
  def Comonad[F[_]](implicit F: Comonad[F]): Comonad[F] = F
  def Cobind[F[_]](implicit F: Cobind[F]): Cobind[F] = F
//  def IsCovariant[F[_]](implicit F: IsCovariant[F]): IsCovariant[F] = F
//  def IsContravariant[F[_]](implicit F: IsContravariant[F]): IsContravariant[F] = F
  def Show[A](implicit A: Show[A]): Show[A] = A
  def Semigroup[T](implicit T: Semigroup[T]): Semigroup[T] = T
  def Monoid[T](implicit T: Monoid[T]): Monoid[T] = T

  // ApplicativeSyntax
  implicit def PapplicativeOpsA[A](a: A): ApplicativeSyntax.OpsA[A] =
    new ApplicativeSyntax.OpsA(a)

  // ApplySyntax
  implicit def PapplyOps[F[_], A](fa: F[A])(
      implicit F: Apply[F]): ApplySyntax.Ops[F, A] =
    new ApplySyntax.Ops(fa)

  // BindSyntax
  implicit def PbindOps[M[_], A](ma: M[A])(
      implicit M: Bind[M]): BindSyntax.Ops[M, A] =
    new BindSyntax.Ops(ma)

  // ComposeSyntax
  implicit def PcomposeOps[P[_, _], A, B](pab: P[A, B])(
      implicit P: Compose[P]): ComposeSyntax.Ops[P, A, B] =
    new ComposeSyntax.Ops(pab)

  // FunctorSyntax
  implicit def PfunctorOps[F[_], A](fa: F[A])(
      implicit F: Functor[F]): FunctorSyntax.Ops[F, A] =
    new FunctorSyntax.Ops(fa)

  // MaybeSyntax
  implicit class POptionAsMaybe[A](oa: Option[A]) {
    def asMaybe: Maybe[A] = Maybe.fromOption(oa)
  }

  implicit def CobindOps[F[_], A](fa: F[A])(
      implicit F: Cobind[F]): CobindSyntax.Ops[F, A] =
    new CobindSyntax.Ops(fa)

  implicit def ComonadOps[F[_], A](fa: F[A])(
      implicit F: Comonad[F]): ComonadSyntax.Ops[F, A] =
    new ComonadSyntax.Ops(fa)

  implicit def ShowOps[A](a: A)(implicit A: Show[A]): ShowSyntax.Ops[A] =
    new ShowSyntax.Ops[A](a)(A)

  implicit def SemigroupOps[A](a: A)(
      implicit A: Semigroup[A]): SemigroupSyntax.OpsA[A] =
    new SemigroupSyntax.OpsA[A](a)

  // Base Data
  // =========

  type \/[L, R] = leibniz.Disjunction.\/[L, R]
//  type ===[A, B] = leibniz.Is[A, B]
//  type <~<[-A, +B] = leibniz.As[A, B]
//  type >~>[+B, -A] = leibniz.As[A, B]
  type Identity[A] = leibniz.Identity[A]

  val Forall: leibniz.Forall.type = leibniz.Forall
  val ∀ : leibniz.Forall.type = leibniz.Forall
  type Forall[F[_]] = Forall.Forall[F]
  type ∀[F[_]] = Forall.Forall[F]

  val Forall2: leibniz.Forall2.type = leibniz.Forall2
  val ∀∀ : leibniz.Forall2.type = leibniz.Forall2
  type Forall2[F[_, _]] = Forall2.Forall2[F]
  type ∀∀[F[_, _]] = Forall2.Forall2[F]

}

object Prelude extends Prelude
