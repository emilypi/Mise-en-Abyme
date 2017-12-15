package cohomolo.gy.prelude

trait Comonad[W[_]] extends Cobind[W] with Functor[W]
