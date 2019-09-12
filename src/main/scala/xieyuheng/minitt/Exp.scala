package xieyuheng.minitt

sealed trait Exp
final case class Var(name: String) extends Exp
final case class Fn(pattern: Pattern, body: Exp) extends Exp
final case class Ap(fn: Exp, arg: Exp) extends Exp
final case class Pi(pattern: Pattern, argType: Exp, t: Exp) extends Exp
final case class Cons(car: Exp, cdr: Exp) extends Exp
final case class Car(pair: Exp) extends Exp
final case class Cdr(pair: Exp) extends Exp
final case class Sigma(pattern: Pattern, argType: Exp, t: Exp) extends Exp
final case class Data(tag: String, body: Exp) extends Exp
final case class Mat(mats: Map[String, Exp]) extends Exp
final case class Sum(mats: Map[String, Exp]) extends Exp
final case object Sole extends Exp
final case object Trivial extends Exp
final case object U extends Exp

sealed trait Pattern
final case class VarPattern(name: String) extends Pattern
final case class ConsPattern(car: Pattern, cdr: Pattern) extends Pattern
final case object SolePattern extends Pattern
