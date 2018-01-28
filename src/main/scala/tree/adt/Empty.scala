package tree.adt

//TODO work with higher kinds instead of this improvisation
case class Empty[A]() extends Tree[A] {
  override def left: Tree[A] = this

  override def right: Tree[A] = this

  override def preOrder: List[Nothing] = List.empty

  override def inOrder: List[Nothing] = List.empty

  override def postOrder: List[Nothing] = List.empty

  override def leveling: List[Nothing] = List.empty

  override def height: Int = -1
}
