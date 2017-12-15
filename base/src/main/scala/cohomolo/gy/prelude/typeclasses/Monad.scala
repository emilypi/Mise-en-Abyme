package cohomolo.gy.prelude.typeclasses

import cohomolo.gy.prelude.syntax.MonadSyntax

trait Monad[M[_]] {
  def applicative: Applicative[M]
  def bind: Bind[M]
}

object Monad extends MonadSyntax {
  def apply[M[_]](implicit M: Monad[M]): Monad[M] = M
}
