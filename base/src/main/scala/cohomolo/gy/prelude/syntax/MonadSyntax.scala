package cohomolo.gy
package prelude
package syntax

import cohomolo.gy.prelude.typeclass.Monad
import com.github.ghik.silencer.silent

import scala.language.experimental.macros
import scala.language.implicitConversions

trait MonadSyntax {
  implicit def monadOps[M[_], A](ma: M[A])(
      implicit M: Monad[M]): MonadSyntax.Ops[M, A] =
    new MonadSyntax.Ops(ma)
}

object MonadSyntax {
  class Ops[M[_]: Monad, A](@silent ma: M[A]) {
    def bind[B](f: A => M[B]): M[B] = macro meta.Ops.f_1
  }
}
