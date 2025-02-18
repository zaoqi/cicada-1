package xieyuheng.tartlet

case class Cdr (
  pair: Exp,
) extends Eliminator {
  def eval(env: Env): Either[Err, Val] = {
    for {
      pairVal <- pair.eval(env)
      res <- Cdr.exe(pairVal)
    } yield res
  }

  def alphaEq(
    that: Exp,
    thisMap: Map[String, String],
    thatMap: Map[String, String],
  ): Boolean = {
    that match {
      case Cdr(pair2) =>
        pair.alphaEq(pair2, thisMap, thatMap)
      case _ => false
    }
  }

  /*
   ctx: p => Sigma(x: A, D)
   ----------------
   ctx :- Cdr(p) => D subst (x, Car(p))
   */
  def infer(ctx: Ctx): Either[Err, The] = {
    for {
      the <- pair.infer(ctx)
      value <- the.t.eval(ctx.toEnv)
      res <- value match {
        case ValSigma(carType, cdrType) =>
          for {
            pairVal <- the.value.eval(ctx.toEnv)
            carVal <- Car.exe(pairVal)
            realCdrType <- cdrType.apply(carVal)
            cdrTypeExp <- realCdrType.readback(ctx, ValUniverse)
          } yield The(cdrTypeExp, the.value)
        case _ =>
          Left(Err(
            s"expected the type to be Sigma(carType, cdrType), found: ${the.t}"))
      }
    } yield res
  }
}

object Cdr {
  def exe(
    pair: Val,
  ): Either[Err, Val] = {
    pair match {
      case ValCons(car, cdr) =>
        Right(cdr)
      case TheNeu(ValSigma(carType, cdrType), neutral) => {
        for {
          carVal <- Car.exe(pair)
          realCdrType <- cdrType.apply(carVal)
        } yield TheNeu(realCdrType, NeuCar(neutral))
      }
      case _ =>
        Left(Err(
          "pair should be " +
            "ValCons(car, cdr) | " +
            "TheNeu(ValSigma(carType, cdrType), neutral): " +
            s"${pair}"))
    }
  }
}
