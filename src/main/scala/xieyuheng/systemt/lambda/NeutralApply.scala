package xieyuheng.systemt

case class NeuAp (
  fn: Neu,
  arg: TheVal,
) extends Neu {
  def readback_neu(used_names: Set[String]): Either[Err, Exp] = {
    for {
      rator <- fn.readback_neu (used_names)
      rand <- arg.readback_the_val (used_names)
    } yield Ap(rator, rand)
  }
}
