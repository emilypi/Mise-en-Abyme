package cohomolo.gy

package prelude

package instances

import typeclass._
import leibniz.{ AMaybe, AJust, AEmpty }

trait AMaybeInstances {

  implicit final def show[F[_, _], A, B](
    implicit FAB: Show[F[A, B]]
  ): Show[AMaybe[F, A, B]] = {
    case AJust(value) => s"AMaybe(${FAB.show(value)})"
    case AEmpty() => "AEmpty()"
  }

}
