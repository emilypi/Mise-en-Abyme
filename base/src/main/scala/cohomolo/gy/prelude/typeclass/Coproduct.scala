package cohomolo.gy

package prelude

package typeclass

trait Coproduct[F[_], G[_]] {

  type λ[A] = Either[F[A], G[A]]
}
