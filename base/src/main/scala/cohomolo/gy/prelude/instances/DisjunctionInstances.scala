package cohomolo.gy

package prelude

package instances

import typeclass._
import impl._
import leibniz.Disjunction.{ \/, -\/, \/- }

trait DisjunctionInstances {
  implicit def monad[L]: Monad[L \/ ?] = new MonadClass.Template[L \/ ?] {

    override def map[A, B](ma: L \/ A)(f: A => B): L \/ B =
      ma.fold[L \/ B](l => -\/(l))(r => \/-(f(r)))

    override def ap[A, B](ma: L \/ A)(mf: L \/ (A => B)): L \/ B =
      ma.fold[L \/ B](l => -\/(l))(a => map[(A => B), B](mf)(f => f(a)))

    override def pure[A](a: A): L \/ A =
      \/-[L, A](a)

    override def bind[A, B](oa: L \/ A)(f: A => L \/ B): L \/ B =
      oa.fold[L \/ B](l => -\/(l))(a => f(a))
  }

  implicit def show[L, R](implicit L: Show[L], R: Show[R]): Show[L \/ R] = {
    case -\/(left) => s"""-\/(${L.show(left)})"""
    case \/-(right) => s"""\/-(${R.show(right)})"""
  }
}
