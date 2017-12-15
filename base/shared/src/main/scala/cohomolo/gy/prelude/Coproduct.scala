package cohomolo.gy.prelude

trait Coproduct[F[_], G[_]] {

  type λ[A] = Either[F[A], G[A]]
}
