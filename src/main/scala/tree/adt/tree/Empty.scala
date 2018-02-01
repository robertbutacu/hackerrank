package tree.adt.tree

//TODO work with higher kinds instead of this improvisation
case class Empty[A]() extends Tree[A] {
  override def left: Tree[A] = this

  override def right: Tree[A] = this

  override def preOrder: List[Nothing] = List.empty

  override def inOrder: List[Nothing] = List.empty

  override def postOrder: List[Nothing] = List.empty

  override def leveling: List[Nothing] = List.empty

  override def height: Int = -1

  override def BFS(): List[A] = List.empty

  override def DFS(): List[A] = List.empty

  override def prune(subtree: Tree[A]): Tree[A] = this

  override def commonAncestor(el1: A, el2: A): Option[A] = None

  override def add(el: A) = TreeBranch(el, Empty(), Empty())

  override def DFS(el: A): Option[List[A]] = None

  override def map[B](f: A => B): Tree[B] = Empty[B]()

  override def forAll(f: A => Boolean): Boolean = true
}
