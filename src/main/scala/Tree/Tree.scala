package Tree

/*
        {ϕ,5}
     0 /     \ 1
    {ϕ,2}   {A,3}
   0/   \1
{B,1}  {C,1}
 */

trait Tree[A] {
  def decode(message: List[Encoded]) = {
    def go(message: List[Encoded], tree: Tree[A]): List[A] = {
      val currEncoding = message.head

      List.empty
    }
  }
}

case class Node[A](left: Tree[A], right: Tree[A]) extends Tree[A]
case class Leaf[A](value: A) extends Tree[A]
case object Empty extends Tree[Nothing]
