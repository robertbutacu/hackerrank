package Tree

/*
        {ϕ,5}
     0 /     \ 1
    {ϕ,2}   {A,3}
   0/   \1
{B,1}  {C,1}
 */

abstract class Tree[A] {
  def decode(message: List[Encoded]): Option[List[A]] = None

  def decodeFirstChar(message: List[Encoded], tree: Tree[A]): Option[Decoded[A]] = None

  def left: Option[Tree[A]]

  def right: Option[Tree[A]]
}


case class Leaf[A](value: Option[A] = None) extends Tree[A] {
  override def left: Option[Tree[A]] = None

  override def right: Option[Tree[A]] = None
}

case class Branch[A](leftBranch: Tree[A], rightBranch: Tree[A]) extends Tree[A] {
  override def left: Option[Tree[A]] = Some(leftBranch)

  override def right: Option[Tree[A]] = Some(rightBranch)
}