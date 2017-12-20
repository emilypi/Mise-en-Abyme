package cohomolo.gy.comonadic

trait YonedaClass[F[_], A] extends Yoneda[F, A] {
  final def Yoneda: Yoneda[F, A] = this
}

object YonedaClass {
  trait Template[F[_], A] extends YonedaClass[F, A] with Run[F, A]

  trait Run[F[_], A] { self: Yoneda[F, A] =>
    override def runYoneda[B](f: A => B): F[B] =
      self.runYoneda(f)
  }

  trait Alt[D <: Alt[D]] { self: D =>
  }
}
