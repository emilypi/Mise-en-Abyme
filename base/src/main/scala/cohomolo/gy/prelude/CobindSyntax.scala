package cohomolo.gy.prelude

import scala.language.implicitConversions
import scala.language.experimental.macros

import cohomolo.gy.meta

import com.github.ghik.silencer.silent

import scala.language.implicitConversions
import scala.language.experimental.macros

trait CobindSyntax {
  implicit def cobindOps[F[_]: Cobind, A](fa: F[A]): CobindSyntax.Ops[F, A] =
    new CobindSyntax.Ops(fa)
}

object CobindSyntax {
  class Ops[F[_]: Cobind, A](@silent fa: F[A]) {
    def cobind[B](f: F[A] => B): F[B] = macro meta.Ops.f_1
  }
}
