package tree.adt

sealed trait Tree[A] {
  def left: Tree[A]
  def right: Tree[A]
}

case class TreeBranch[A](value: A, left: Tree[A], right: Tree[A]) extends Tree[A]

case object Empty extends Tree[Nothing] {
  override def left: Tree[Nothing] = Empty

  override def right: Tree[Nothing] = Empty
}
