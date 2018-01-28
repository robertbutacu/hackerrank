package tree.adt

/*
        {ϕ,5}
     0 /     \ 1
    {ϕ,2}   {A,3}
   0/   \1
{B,1}  {C,1}
 */

sealed trait HuffmanEncodingTree[A] {
  def left: HuffmanEncodingTree[A]

  def right: HuffmanEncodingTree[A]
}


case class Leaf[A](value: Option[A] = None) extends HuffmanEncodingTree[A] {
  override def left: HuffmanEncodingTree[A] = this

  override def right: HuffmanEncodingTree[A] = this
}

case class Branch[A](leftBranch: HuffmanEncodingTree[A],
                     rightBranch: HuffmanEncodingTree[A]) extends HuffmanEncodingTree[A] {
  override def left: HuffmanEncodingTree[A] = leftBranch

  override def right: HuffmanEncodingTree[A] = rightBranch
}