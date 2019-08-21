package xieyuheng.tt.tartlet

case object Universe extends Type {
  def eval(env: Env): Either[ErrorMsg, Value] =
    Right(ValueUniverse)

  def alphaEq(
    that: Exp,
    thisMap: Map[String, String],
    thatMap: Map[String, String],
  ): Boolean = {
    that match {
      case Universe => true
      case _ => false
    }
  }

  /*
   -----------------
   ctx :- Universe => Universe
   */
  def infer(ctx: Ctx): Either[ErrorMsg, The] =
    Right(The(Universe, Universe))
}