package cohomolo.gy.prelude

trait Monad[M[_]] extends Applicative[M] with Bind[M] with Apply[M]
