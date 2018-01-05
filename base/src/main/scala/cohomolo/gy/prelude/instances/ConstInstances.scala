package cohomolo.gy.prelude.instances

import cohomolo.gy.prelude.typeclass.{ Apply, Const, Functor, Semigroup }

trait ConstInstances {

  implicit def functor[R]: Functor[Const[R, ?]] = new Functor[Const[R, ?]] {

    def map[A, B](fa: Const[R, A])(f: A => B): Const[R, B] =
      fa.retag[B]
  }

  implicit def apply[R](implicit R: Semigroup[R]): Apply[Const[R, ?]] =
    new Apply[Const[R, ?]] {
      def functor: Functor[Const[R, ?]] = Const.functor[R]

      def ap[A, B](fa: Const[R, A])(f: Const[R, A => B]): Const[R, B] =
        Const(R.append(fa.getConst, f.getConst))
    }

  implicit def semigroup[A, B](
    implicit A: Semigroup[A]
  ): Semigroup[Const[A, B]] =
    new Semigroup[Const[A, B]] {

      def append(a1: Const[A, B], a2: =>Const[A, B]): Const[A, B] =
        Const(A.append(a1.getConst, a2.getConst))
    }

}
