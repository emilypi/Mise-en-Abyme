package cohomolo.gy

package prelude

package leibniz

import cohomolo.gy.prelude.instances.IdentityInstances

final case class Identity[A](run: A) extends AnyVal

trait IdentityTypes {
  type Id[X] = X
}

object Identity extends IdentityTypes with IdentityInstances
