package Tree

case class MessageDecoder[A](tree: Tree[A]) {
  def decode(message: List[Encoded]): Option[List[A]] = {
    def go(message: List[Encoded], tree: Tree[A]): Option[List[A]] = {
      val decodedFirst = decodeFirstChar(message)

      val decodings = for {
        d <- decodedFirst.toList
        decoded <- d.decoding
        if d.remainingMessage.nonEmpty
        toBeDecoded <- go(d.remainingMessage, tree)
      } yield List(decoded) ::: toBeDecoded

      val result = for {
        decodingLetters <- decodings
        decodedLetter <- decodingLetters
      } yield decodedLetter

      if (result.isEmpty) None
      else Some(result)

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

    println(message)

    if(message.isEmpty) None
    else go(message, tree)
  }
}
