package tree.adt.tree

//TODO work with higher kinds instead of this improvisation
case class BinaryTreeEmpty[A]() extends Tree[A] {
  override def left: Tree[A] = this

  override def right: Tree[A] = this

  /**
    *
    *        1
    *
    *   2            3
    *
    * 4    5       6    7
    *
    * PreOrder: 1 2 4 5 3 6 7
    *
    * @return An empty list since it has no elements
    */
  override def preOrder: List[Nothing] = List.empty

  /**
    *
    *
    *         1
    *
    *     2            3
    *
    * 4    5       6    7
    *
    * InOrder: 4 2 5 1 6 3 7
    *
    * @return An empty list since it has no elements
    */
  override def inOrder: List[Nothing] = List.empty
  /**
    *
    *
    *          1
    *
    *     2            3
    *
    * 4    5       6    7
    *
    * PostOrder: 4 5 2 6 7 3 1
    *
    * @return An empty list since it has no elements
    */
  override def postOrder: List[Nothing] = List.empty

  override def leveling: List[Nothing] = List.empty

  override def height: Int = -1

  override def BFS(): List[A] = List.empty

  override def DFS(): List[A] = List.empty

  override def prune(subtree: Tree[A]): Tree[A] = this

  override def commonAncestor(el1: A, el2: A): Option[A] = None

  override def add(el: A) = BinaryTreeBranch(el, BinaryTreeEmpty(), BinaryTreeEmpty())

  override def DFS(el: A): Option[List[A]] = None

  /**
    *
    * @param f => a function used to transform all the values of the Tree
    * @tparam B => the type of the future-to-be value
    * @return an empty Tree since it has no elements
    */
  override def map[B](f: A => B): Tree[B] = BinaryTreeEmpty[B]()

  /**
    *
    * @param f => a boolean function which will be applied over all values
    * @return true, since anything else will mess up the logic
    */
  override def forAll(f: A => Boolean): Boolean = true

  override def swap(k: Int): Tree[A] = this

  override def swaps(k: List[Int]): Tree[A] = this
}
