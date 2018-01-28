package Tree

/*
        {ϕ,5}
     0 /     \ 1
    {ϕ,2}   {A,3}
   0/   \1
{B,1}  {C,1}
 */

abstract class Tree[A] {
  def left: Tree[A]

  def right: Tree[A]
}


case class Leaf[A](value: Option[A] = None) extends Tree[A] {
  override def left: Tree[A] = this

  override def right: Tree[A] = this
}

case class Branch[A](leftBranch: Tree[A], rightBranch: Tree[A]) extends Tree[A] {
  override def left: Tree[A] = leftBranch

  override def right: Tree[A] = rightBranch
}