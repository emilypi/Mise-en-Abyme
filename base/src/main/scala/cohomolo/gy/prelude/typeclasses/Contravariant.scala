package cohomolo.gy.prelude.typeclasses

trait Contravariant[F[_]] {
  def contramap[A, B](r: F[A])(f: B => A): F[B]
}
