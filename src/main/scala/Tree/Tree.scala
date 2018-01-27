package Tree

/*
        {ϕ,5}
     0 /     \ 1
    {ϕ,2}   {A,3}
   0/   \1
{B,1}  {C,1}
 */

trait Tree[A] {

  case class Decoded(decoding: A, remainingMessage: List[Encoded])

  def decode(message: List[Encoded], tree: Tree[A]): Option[List[A]] = {
    def go(message: List[Encoded], tree: Tree[A]): Option[List[A]] = {

      val currEncoding = message.head

      None
    }

    go(message, tree)
  }

  def decodeFirstChar(message: List[Encoded], tree: Tree[A]): Option[Decoded] = {
    val currEncoding = message.head

    currEncoding match {
      case Zero => decodeFirstChar(message.tail, tree.left)
      case One => decodeFirstChar(message.tail, tree.right)
    }
  }

  def left: Tree[A]

  def right: Tree[A]
}

case class Leaf[A](value: A) extends Tree[A] {
  override def left: Tree[Nothing] = Empty

  override def right: Tree[Nothing] = Empty
}


case object Empty extends Tree[Nothing] {
  override def left: Tree[Nothing] = this

  override def right: Tree[Nothing] = this
}


case class Node[A](left: Tree[A], right: Tree[A]) extends Tree[A] {
}