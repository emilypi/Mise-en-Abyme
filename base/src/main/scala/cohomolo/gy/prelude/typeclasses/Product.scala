package cohomolo.gy.prelude.typeclasses

trait Product[F[_], G[_]] {
  type λ[A] = (F[A], G[A])
}
