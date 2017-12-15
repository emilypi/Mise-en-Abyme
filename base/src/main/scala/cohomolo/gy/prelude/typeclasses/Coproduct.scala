package cohomolo.gy.prelude.typeclasses

trait Coproduct[F[_], G[_]] {

  type λ[A] = Either[F[A], G[A]]
}
