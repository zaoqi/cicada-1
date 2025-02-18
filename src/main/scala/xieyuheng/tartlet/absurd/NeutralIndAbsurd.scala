package xieyuheng.tartlet

case class NeuIndAbsurd (
  target: Neu,
  motive: TheVal,
) extends Neu {
  def readback_neu (ctx: Ctx): Either[Err, Exp] = {
    for {
      target <- target.readback_neu(ctx)
      motive <- motive.readback_the_val(ctx)
    } yield IndAbsurd(The(Absurd, target), motive)
  }
}
