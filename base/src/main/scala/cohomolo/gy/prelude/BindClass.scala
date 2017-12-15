package cohomolo.gy.prelude

trait BindClass[M[_]] extends Bind[M] with ApplyClass[M] {
  final def bind: Bind[M] = this
}

object BindClass {
  trait Template[M[_]] extends BindClass[M] with Ap[M]

  trait Ap[M[_]] { self: Bind[M] with Apply[M] with Functor[M] =>
    override def ap[A, B](fa: M[A])(f: M[A => B]): M[B] = bind(f)(map(fa))
  }

  trait FlatMap[M[_]] extends Alt[FlatMap[M]] { self: Bind[M] =>
    override def bind[A, B](ma: M[A])(f: A => M[B]): M[B]
    override def join[A](ma: M[M[A]]): M[A] = bind(ma)(identity)
  }
  trait Flatten[M[_]] extends Alt[Flatten[M]] { self: Bind[M] =>
    override def join[A](ma: M[M[A]]): M[A]
    override def bind[A, B](ma: M[A])(f: (A) => M[B]): M[B] =
      join(apply.functor.map(ma)(f))
  }

  trait Alt[D <: Alt[D]] { self: D =>
  }
}
