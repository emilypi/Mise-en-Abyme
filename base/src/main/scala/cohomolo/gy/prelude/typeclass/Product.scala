package cohomolo.gy
package prelude
package typeclass

trait Product[F[_], G[_]] {
  type λ[A] = (F[A], G[A])
}
