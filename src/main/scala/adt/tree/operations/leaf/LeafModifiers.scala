package adt.tree.operations.leaf

import adt.tree.operations.TreeModifiers
import adt.tree.{BinaryTreeBranch, BinaryTreeEmpty, Tree}

object LeafModifiers {

  implicit class LeafModifiers[A: Ordering](tree: BinaryTreeEmpty[A]) extends TreeModifiers[A] {

    override def prune(subtree: Tree[A]): Tree[A] = tree

    override def add(el: A) = BinaryTreeBranch(el, BinaryTreeEmpty(), BinaryTreeEmpty())

    /**
      * @param f => a function used to transform all the values of the Tree
      * @tparam B => the type of the future-to-be value
      * @return an empty Tree since it has no elements
      */
    override def map[B: Ordering](f: A => B): Tree[B] = BinaryTreeEmpty[B]()

    /**
      * @param k => the level where the left and right trees will be swapped
      * @return => the tree on k level swapped . However, empty tree => empty result.
      */
    override def swap(k: Int): Tree[A] = tree

    override def filter(f: A => Boolean): List[A] = List.empty[A]

    override def filterNot(f: A => Boolean): List[A] = List.empty[A]

    override def merge(tree: Tree[A]): Tree[A] = tree

    override def addAll(elements: List[A]): Tree[A] = {
      def go(tree: Tree[A], elements: List[A]): Tree[A] =
        elements.foldRight(tree)((curr, acc) => acc.add(curr))

      go(tree, elements)
    }

    override def swapAll(ks: List[Int]): Tree[A] = tree
  }

}

