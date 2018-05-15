package adt.tree.operations.branch

import adt.tree.{BinaryTreeBranch, BinaryTreeEmpty, Tree}
import adt.tree.operations.TreeLookup

import scala.annotation.tailrec

object BranchLookup {
  implicit class BranchLookup[A](tree: BinaryTreeBranch[A]) extends TreeLookup[A]{
    /**
      *
      *         1
      *
      *    2            3
      *
      * 4    5       6    7
      *
      * 4 5 2 6 7 3 1
      *
      * @return the preOrder traversal of the tree
      */
    override def preOrder: List[A] =
      List(tree.value) ::: tree.left.preOrder ::: tree.right.preOrder

    /**
      *
      *          1
      *
      *   2            3
      *
      * 4    5       6    7
      *
      * 4 2 5 1 6 3 7
      *
      * @return the inOrder traversal of the tree
      */
    override def inOrder: List[A] =
      tree.left.inOrder ::: List(tree.value) ::: tree.right.inOrder

    /**
      *
      *         1
      *
      *   2            3
      *
      * 4    5       6    7
      *
      * 4 5 2 6 7 3 1
      *
      * @return the postOrder traversal of the Tree
      */
    override def postOrder: List[A] =
      tree.left.postOrder ::: tree.right.postOrder ::: List(tree.value)

    /**
      *
      *
      * @return
      */
    override def leveling: List[A] = {
      @tailrec
      def go(curr: Tree[A], queue: List[Tree[A]], result: List[A]): List[A] = {
        val updatedQueue = (queue ::: List(curr.left) ::: List(curr.right))
          .filterNot(_ == BinaryTreeEmpty())
        val updatedResult = result ::: (curr match {
          case BinaryTreeBranch(v, _, _) => List(v)
          case _ => Nil
        })

        if (updatedQueue.isEmpty)
          updatedResult
        else
          go(updatedQueue.head, updatedQueue.tail, updatedResult)
      }

      go(tree, List.empty, List.empty)
    }

    /**
      *         1
      *
      *     2            3
      *
      * 4    5       6    7
      *
      * => 2
      * @return the height of the tree, where level 0 has height 0
      */
    override def height: Int = {
      val leftHeight = tree.left.height + 1
      val rightHeight = tree.right.height + 1

      Math.max(leftHeight, rightHeight)
    }

    /**
      *           1
      *
      *   2            3
      *
      * 4    5       6    7
      *
      * => 1 2 3 4 5 6 7
      * @return the breadth first search of the tree
      */
    override def BFS(): List[A] =
      this.leveling

    /**
      *         1
      *
      *   2            3
      *
      * 4    5       6    7
      *
      * => 1 2 4 5 3 6 7
      * @return the depth first search ordering of the tree
      */
    override def DFS(): List[A] = List(tree.value) ::: tree.left.DFS() ::: tree.right.DFS()

    /**
      * The algorithm does a breadth first search for e1 and e2.
      * Having that, the results will look something like:
      * 1 4 5 6 7 and 1 4 9 8 5
      * With that result, all that is needed to be done is take while the zipped elements are equal
      * and return the last element - that will represent the last common ancestor of e1 and e2
      *
      * @param el1 => first element
      * @param el2 => second element
      * @return the common ancestor of the elements, if that exists
      */
    override def commonAncestor(el1: A, el2: A): Option[A] = {
      for {
        firstDFS <- this.DFS(el1)
        secondDFS <- this.DFS(el2)
      } yield firstDFS.zip(secondDFS)
        .takeWhile(e => e._1 == e._2)
        .last
        ._1
    }


    /**
      * The algorithm is a variation of BFS() which stops at first encountering with el.
      *
      * road: List[A] represents the road to the current tree
      *
      * @param el =>  the element to be searched
      * @return => an optional list of the path to el
      */
    override def DFS(el: A): Option[List[A]] = {
      def go(curr: Tree[A], road: List[A]): Option[List[A]] = {
        curr match {
          case BinaryTreeEmpty() => None
          case BinaryTreeBranch(v, l, r) =>
            if (v == el)
              Some(road :+ v)
            else {
              val left = go(l, road :+ v)
              val right = go(r, road :+ v)

              left orElse right
            }
        }
      }

      go(tree, List.empty)
    }

    /**
      * Recursively go deeper into the BST to find the element, if it exists
      *
      * @param f - a function returning a boolean for which an element will need to be found
      * @return an element fulfilling the according function
      */
    override def find(f: A => Boolean): Option[A] =
      if (f(tree.value)) Some(tree.value)
      else tree.left.find(f).orElse(tree.right.find(f))
  }
}
