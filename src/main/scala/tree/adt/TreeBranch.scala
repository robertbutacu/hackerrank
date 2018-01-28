package tree.adt

case class TreeBranch[A](value: A, left: Tree[A], right: Tree[A]) extends Tree[A] {
  override def preOrder: List[A] = List(this.value) ::: this.left.preOrder ::: this.right.preOrder

  override def inOrder: List[A] = this.left.inOrder ::: List(this.value) ::: this.right.inOrder

  override def postOrder: List[A] = this.left.postOrder ::: this.right.postOrder ::: List(this.value)

  override def leveling: List[A] = List.empty

  override def height: Int = {
    val leftHeight = this.left.height + 1
    val rightHeight = this.right.height + 1

    Math.max(leftHeight, rightHeight)
  }
}
