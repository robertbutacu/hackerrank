package tree.adt

sealed trait Tree[A] {
  def left: Tree[A]

  def right: Tree[A]

  def preOrder: List[A]

  def inOrder: List[A]

  def postOrder: List[A]

  def leveling: List[A]
}

case class TreeBranch[A](value: A, left: Tree[A], right: Tree[A]) extends Tree[A] {
  override def preOrder: List[A] = List(this.value) ::: this.left.preOrder ::: this.right.preOrder

  override def inOrder: List[A] = this.left.inOrder ::: List(this.value) ::: this.right.inOrder

  override def postOrder: List[A] = this.left.postOrder ::: this.right.postOrder ::: List(this.value)

  override def leveling: List[A] = List.empty
}

//TODO work with higher kinds instead of this improvisation
case class Empty[A]() extends Tree[A] {
  override def left: Tree[A] = this

  override def right: Tree[A] = this

  override def preOrder: List[Nothing] = List.empty

  override def inOrder: List[Nothing] = List.empty

  override def postOrder: List[Nothing] = List.empty

  override def leveling: List[Nothing] = List.empty
}
