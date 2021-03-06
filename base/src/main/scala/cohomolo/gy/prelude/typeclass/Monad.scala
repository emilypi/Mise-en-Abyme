package cohomolo.gy

package prelude

package typeclass

import cohomolo.gy.prelude.functions.MonadFunctions
import cohomolo.gy.prelude.syntax.MonadSyntax

trait Monad[M[_]] {
  def applicative: Applicative[M]
  def bind: Bind[M]
}

object Monad extends MonadSyntax with MonadFunctions {
  def apply[M[_]](implicit M: Monad[M]): Monad[M] = M
}
