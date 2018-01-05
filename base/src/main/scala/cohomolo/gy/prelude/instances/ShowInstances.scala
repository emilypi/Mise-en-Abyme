package cohomolo.gy.prelude.instances

import cohomolo.gy.prelude.typeclass.{ Contravariant, Show }

trait ShowInstances {

  implicit final def string: Show[String] = s => s

  implicit final def contravariant: Contravariant[Show] =
    new Contravariant[Show] {

      def contramap[A, B](r: Show[A])(f: B => A): Show[B] =
        b => r.show(f(b))
    }
}
