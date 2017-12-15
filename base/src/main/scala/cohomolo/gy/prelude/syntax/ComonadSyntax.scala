package cohomolo.gy.prelude.syntax

import cohomolo.gy.meta
import cohomolo.gy.prelude.typeclasses.Comonad
import com.github.ghik.silencer.silent

import scala.language.experimental.macros
import scala.language.implicitConversions

trait ComonadSyntax {
  implicit def comonadOps[F[_], A](fa: F[A])(
      implicit F: Comonad[F]): ComonadSyntax.Ops[F, A] =
    new ComonadSyntax.Ops(fa)
}

object ComonadSyntax {
  class Ops[F[_]: Comonad, A](@silent self: F[A]) {
    def copoint: A = macro meta.Ops.f_0
  }
}
