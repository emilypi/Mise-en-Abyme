package cohomolo.gy.prelude

trait Product[F[_], G[_]] {
  type λ[A] = (F[A], G[A])
}
