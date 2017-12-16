package cohomolo.gy
package prelude
package syntax

import leibniz.Maybe

trait MaybeSyntax {
  implicit class OptionAsMaybe[A](oa: Option[A]) {
    def asMaybe: Maybe[A] = Maybe.fromOption(oa)
  }
}
