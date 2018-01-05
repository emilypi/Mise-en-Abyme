package cohomolo.gy.prelude.functions

import cohomolo.gy.prelude.typeclass.Monad

trait MonadFunctions {

  def liftM[M[_], A, B](ma: M[A])(f: A => B)(implicit M: Monad[M]): M[B] =
    M.applicative.apply.ap(ma)(M.applicative.pure(f))
}
