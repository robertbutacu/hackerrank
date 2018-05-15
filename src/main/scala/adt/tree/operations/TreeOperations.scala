package adt.tree.operations

import adt.tree.{BinaryTreeBranch, Tree}

object TreeOperations {
  implicit class TreeOperations[A](tree: Tree[A])extends TreeLookup[A] with TreeModifiers[A] {
    override def preOrder: List[A] = tree match {
      case t: BinaryTreeBranch[A] => t.preOrder
    }

    override def inOrder: List[A] = ???

    override def postOrder: List[A] = ???

    override def leveling: List[A] = ???

    override def height: Int = ???

    override def BFS(): List[A] = ???

    override def DFS(): List[A] = ???

    override def DFS(el: A): Option[List[A]] = ???

    override def commonAncestor(el1: A, el2: A): Option[A] = ???

    override def find(f: A => Boolean): Option[A] = ???

    override def prune(subtree: Tree[A]): Tree[A] = ???

    override def add(el: A): Tree[A] = ???

    override def map[B: Ordering](f: A => B): Tree[B] = ???

    override def swap(k: Int): Tree[A] = ???

    override def swapAll(ks: List[Int]): Tree[A] = ???

    override def filter(f: A => Boolean): List[A] = ???

    override def filterNot(f: A => Boolean): List[A] = ???

    override def merge(tree: Tree[A]): Tree[A] = ???

    override def addAll(elements: List[A]): Tree[A] = ???
  }
}
