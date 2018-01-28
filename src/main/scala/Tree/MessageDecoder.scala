package Tree

case class MessageDecoder[A](tree: Tree[A]) {
  def decode(message: List[Encoded]): Option[List[A]] = {
    def go(message: List[Encoded], tree: Tree[A]): Option[List[A]] = {
      val decodedFirst = decodeFirstChar(message)

      //println(decodedFirst)

      val decodings = for {
        d <- decodedFirst
        decoded <- d.decoding
      } yield decoded

      val restOfDecoding = for {
        decoding <- decodedFirst
        if decoding.remainingMessage.nonEmpty
        remaining <- decode(decoding.remainingMessage)
      } yield remaining

      restOfDecoding match {
        case None => decodings.map(d => List(d))
        case Some(d) => decodings.map(j => d :+ j)
      }
    }

    go(message, tree)
  }

  def decodeFirstChar(message: List[Encoded]): Option[Decoded[A]] = {
    def go(message: List[Encoded], tree: Tree[A]): Option[Decoded[A]] = {
      tree match {
        case Leaf(value) => Option.apply(Decoded(value, message))
        case _ =>
          message.head match {
            case Zero => go(message.tail, tree.left)
            case One => go(message.tail, tree.right)
          }
      }
    }

    if (message.isEmpty) None
    else go(message, tree)
  }
}
