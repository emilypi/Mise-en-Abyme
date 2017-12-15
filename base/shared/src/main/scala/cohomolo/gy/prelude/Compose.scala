package cohomolo.gy.prelude

trait Compose[F[_], G[_]] {
  type λ[A] = F[G[A]]
}
