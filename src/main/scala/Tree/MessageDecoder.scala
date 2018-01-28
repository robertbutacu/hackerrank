package Tree

case class MessageDecoder[A](tree: Tree[A]) {
  def decode(message: List[Encoded]): Option[List[A]] = {
    None
  }

  def decodeFirstChar(message: List[Encoded]): Option[Decoded[A]] = {
    def go(message: List[Encoded], tree: Tree[A]): Option[Decoded[A]] = {
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
