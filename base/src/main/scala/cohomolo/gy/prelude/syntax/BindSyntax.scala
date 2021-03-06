package cohomolo.gy

package prelude

package syntax

import cohomolo.gy.prelude.typeclass.Bind
import com.github.ghik.silencer.silent

import scala.language.experimental.macros
import scala.language.implicitConversions

trait BindSyntax {
  implicit def bindOps[M[_], A](
    ma: M[A]
  )(implicit M: Bind[M]): BindSyntax.Ops[M, A] =
    new BindSyntax.Ops(ma)
}

object BindSyntax {

  class Ops[M[_]: Bind, A](@silent ma: M[A]) {
    def bind[B](f: A => M[B]): M[B] = macro meta.Ops.f_1
  }
}
