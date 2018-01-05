package cohomolo.gy

package prelude

package typeclass

import cohomolo.gy.prelude.functions.BindFunctions
import cohomolo.gy.prelude.syntax.BindSyntax

trait Bind[M[_]] {
  def apply: Apply[M]
  def bind[A, B](ma: M[A])(f: A => M[B]): M[B]
  def join[A](ma: M[M[A]]): M[A]
}

object Bind extends BindFunctions with BindSyntax {
  def apply[M[_]](implicit M: Bind[M]): Bind[M] = M
}
