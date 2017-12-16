package cohomolo.gy.prelude.impl

import cohomolo.gy.prelude._
import cohomolo.gy.prelude.typeclass.{Applicative, Apply, Functor}

trait ApplicativeClass[F[_]] extends Applicative[F] with ApplyClass[F] {
  final def applicative: Applicative[F] = this
}

object ApplicativeClass {
  trait Template[F[_]] extends ApplicativeClass[F] with Map[F] with LiftA[F]

  trait LiftA[F[_]] { self: Applicative[F] with Apply[F] with Functor[F] =>
    def liftA[A, B](fa: F[A])(f: A => B): F[B] = map(fa)(f)
    def liftA2[A, B, C](fa: F[A])(fb: F[B])(f: A => B => C): F[C] =
      ap(fb)(map(fa)(f))
    def liftA3[A, B, C, D](fa: F[A])(fb: F[B])(fc: F[C])(
        f: A => B => C => D): F[D] = ap(fc)(liftA2(fa)(fb)(f))
  }

  trait Map[F[_]] { self: Applicative[F] with Apply[F] with Functor[F] =>
    override def map[A, B](ma: F[A])(f: (A) => B): F[B] = ap(ma)(pure(f))
  }
}
