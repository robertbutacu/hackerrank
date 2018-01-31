package tree.adt.tree

trait Tree[A] {
  def left: Tree[A]

  def right: Tree[A]

  def preOrder: List[A]

  def inOrder: List[A]

  def postOrder: List[A]

  def leveling: List[A]

  def height: Int

  def BFS(): List[A]

  def DFS(): List[A]

  def DFS(el: A): Option[List[A]]

  def prune(subtree: Tree[A]): Tree[A]

  // in order to do so, perform DFS(el1), DFS(el2)
  // and, a take while _ == _ and the last element is the common ancestor
  def commonAncestor(el1: A, el2: A): Option[A]

  def add(el: A): Tree[A]
}
