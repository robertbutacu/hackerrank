package adt.tree

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

  def commonAncestor(el1: A, el2: A): Option[A]

  def add(el: A): Tree[A]

  def map[B: Ordering](f: A => B): Tree[B]

  def swap(k: Int): Tree[A]

  def swaps(ks: List[Int]): Tree[A]

  def forAll(f: A => Boolean): Boolean

  def find(f: A => Boolean): Option[A]

  def filter(f: A => Boolean): List[A]

  def filterNot(f: A => Boolean): List[A]

  def merge(tree: Tree[A]): Tree[A]

  def toList: List[A]

  def addAll(elements: List[A]): Tree[A]
}
