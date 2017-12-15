package cohomolo.gy.prelude

import cohomolo.gy.prelude
import cohomolo.gy.prelude.syntax._

import scala.language.implicitConversions


trait Prelude  extends
  prelude.functions.BindFunctions
  with prelude.functions.FunctorFunctions
  with prelude.functions.CobindFunctions {

  // Base Class
  // ==========
  type Applicative[F[_]] = typeclasses.Applicative[F]
  type Apply[F[_]] = typeclasses.Apply[F]
  type Bind[M[_]] = typeclasses.Bind[M]
  type Compose[P[_,_]] = typeclasses.Compose[P]
  type Functor[F[_]] = typeclasses.Functor[F]
  type Monad[M[_]] = typeclasses.Monad[M]
  type Comonad[F[_]] = typeclasses.Comonad[F]
  type Cobind[F[_]] = typeclasses.Cobind[F]
  type Show[A] = typeclasses.Show[A]
  type Semigroup[T] = typeclasses.Semigroup[T]
  type Monoid[T] = typeclasses.Monoid[T]

  def Applicative[F[_]](implicit F: Applicative[F]): Applicative[F] = F
  def Apply[F[_]](implicit F: Apply[F]): Apply[F] = F
  def Bind[F[_]](implicit F: Bind[F]): Bind[F] = F
  def Compose[P[_,_]](implicit P: Compose[P]): Compose[P] = P
  def Functor[F[_]](implicit F: Functor[F]): Functor[F] = F
  def Monad[M[_]](implicit M: Monad[M]): Monad[M] = M
  def Comonad[F[_]](implicit F: Comonad[F]): Comonad[F] = F
  def Cobind[F[_]](implicit F: Cobind[F]): Cobind[F] = F
  def Show[A](implicit A: Show[A]): Show[A] = A
  def Semigroup[T](implicit T: Semigroup[T]): Semigroup[T] = T
  def Monoid[T](implicit T: Monoid[T]): Monoid[T] = T

  // ApplicativeSyntax
  implicit def PapplicativeOpsA[A](a: A): ApplicativeSyntax.OpsA[A] = new ApplicativeSyntax.OpsA(a)

  // ApplySyntax
  implicit def PapplyOps[F[_], A](fa: F[A])(implicit F: Apply[F]): ApplySyntax.Ops[F, A] =
    new ApplySyntax.Ops(fa)

  // BindSyntax
  implicit def PbindOps[M[_], A](ma: M[A])(implicit M: Bind[M]): BindSyntax.Ops[M, A] =
    new BindSyntax.Ops(ma)

  // ComposeSyntax
  implicit def PcomposeOps[P[_, _], A, B](pab: P[A, B])(implicit P: Compose[P]): ComposeSyntax.Ops[P, A, B] =
    new ComposeSyntax.Ops(pab)

  // FunctorSyntax
  implicit def PfunctorOps[F[_], A](fa: F[A])(implicit F: Functor[F]): FunctorSyntax.Ops[F, A] =
    new FunctorSyntax.Ops(fa)

  implicit def CobindOps[F[_], A](fa: F[A])(implicit F: Cobind[F]): CobindSyntax.Ops[F, A] =
    new CobindSyntax.Ops(fa)

  implicit def ComonadOps[F[_], A](fa: F[A])(implicit F: Comonad[F]): ComonadSyntax.Ops[F, A] =
    new ComonadSyntax.Ops(fa)

  implicit def ShowOps[A](a: A)(implicit A: Show[A]): ShowSyntax.Ops[A] =
    new ShowSyntax.Ops[A](a)(A)

  implicit def SemigroupOps[A](a: A)(implicit A: Semigroup[A]): SemigroupSyntax.OpsA[A] =
    new SemigroupSyntax.OpsA[A](a)


  // Base Data
  // =========

//  type \/[L, R] = data.Disjunction.\/[L, R]
//  type ===[A, B] = data.Is[A, B]
//  type <~<[-A, +B] = As[A, B]
//  type >~>[+B, -A] = As[A, B]
//  type Identity[A] = data.Identity[A]
//  type Forget[A, B, C] = data.Forget[A, B, C]
//
//  val Forall : data.Forall.type = data.Forall
//  val ∀      : data.Forall.type = data.Forall
//  type Forall[F[_]]             = Forall.Forall[F]
//  type ∀[F[_]]                  = Forall.Forall[F]
//
//  val Forall2 : data.Forall2.type = data.Forall2
//  val ∀∀      : data.Forall2.type = data.Forall2
//  type Forall2[F[_, _]]           = Forall2.Forall2[F]
//  type ∀∀[F[_, _]]                = Forall2.Forall2[F]

}

object Prelude extends Prelude
