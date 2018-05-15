package adt.tree.operations

trait TreeLookup[A] {
  def preOrder: List[A]

  def inOrder: List[A]

  def postOrder: List[A]

  def leveling: List[A]

  def height: Int

  def BFS(): List[A]

  def DFS(): List[A]

  def DFS(el: A): Option[List[A]]

  def commonAncestor(el1: A, el2: A): Option[A]

  def find(f: A => Boolean): Option[A]
}
