package adt.tree.operations.leaf

import adt.tree.BinaryTreeEmpty
import adt.tree.operations.TreeLookup

object LeafLookup {
  implicit class LeafLookup[A](tree: BinaryTreeEmpty[A]) extends TreeLookup[A]{
    /**
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
    override def preOrder: List[A] = List.empty[A]

    /**
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
    override def inOrder: List[A] = List.empty[A]
    /**
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
    override def postOrder: List[A] = List.empty[A]

    override def leveling: List[A] = List.empty[A]

    override def height: Int = -1

    override def BFS(): List[A] = List.empty[A]

    override def DFS(): List[A] = List.empty[A]

    override def commonAncestor(el1: A, el2: A): Option[A] = None

    override def DFS(el: A): Option[List[A]] = None

    override def find(f: A => Boolean): Option[A] = None
  }
}
