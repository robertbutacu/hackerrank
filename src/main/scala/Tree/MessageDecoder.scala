package Tree

case class MessageDecoder[A](tree: Tree[A]) {
  def decode(message: List[Encoded]): Option[List[A]] = {
    def go(message: List[Encoded], tree: Tree[A]): Option[List[A]] = {
      val decodedFirst = decodeFirstChar(message)

      None
    }

    go(message, tree)
  }

  def decodeFirstChar(message: List[Encoded]): Option[Decoded[Option]] = {
    def go(message: List[Encoded], tree: Tree[A]): Option[Decoded[Option]] = {
      tree match {
        case Leaf(value) => Some(Decoded(value, message))
        case _           =>
          message.head match {
            case Zero => go(message.tail, tree.left)
            case One  => go(message.tail, tree.right)
          }
      }
    }

    go(message, tree)
  }
}
