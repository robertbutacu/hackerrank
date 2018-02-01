package tree.adt.tree

import scala.annotation.tailrec

case class TreeBranch[A](value: A, left: Tree[A], right: Tree[A]) extends Tree[A] {
  override def preOrder: List[A] = List(this.value) ::: this.left.preOrder ::: this.right.preOrder

  override def inOrder: List[A] = this.left.inOrder ::: List(this.value) ::: this.right.inOrder

  override def postOrder: List[A] = this.left.postOrder ::: this.right.postOrder ::: List(this.value)

  override def leveling: List[A] = List.empty

  override def height: Int = {
    val leftHeight = this.left.height + 1
    val rightHeight = this.right.height + 1

    Math.max(leftHeight, rightHeight)
  }

  override def BFS(): List[A] = {
    @tailrec
    def go(curr: Tree[A], queue: List[Tree[A]], bfs: List[A]): List[A] = {
      go(queue.head, queue.tail ::: List(curr.left, curr.right), curr match {
        case Empty() => bfs
        case TreeBranch(v, _, _) => bfs :+ v
      })
    }

    go(this, List.empty, List.empty)
  }

  override def DFS(): List[A] = this.left.BFS() ::: List(this.value) ::: this.right.BFS()

  override def prune(subtree: Tree[A]): Tree[A] = ???

  override def commonAncestor(el1: A, el2: A): Option[A] = {
    for {
      firstDFS <- this.DFS(el1)
      secondDFS <- this.DFS(el2)
    } yield firstDFS.zip(secondDFS)
      .takeWhile(e => e._1 == e._2)
      .last
      ._1
  }

  override def add(el: A): Tree[A] = ???

  override def DFS(el: A): Option[List[A]] = {
    def go(curr: Tree[A], road: List[A]): Option[List[A]] = {
      curr match {
        case Empty() => None
        case TreeBranch(v, l, r) =>
          if (v == el)
            Some(road :+ v)
          else {
            val left  = go(l, road :+ v)
            val right = go(r, road :+ v)

            left orElse right
          }
      }
    }

    go(this, List.empty)
  }

  override def map[B](f: A => B) = TreeBranch(f(this.value), left.map(f), right.map(f))

  override def forAll(f: A => Boolean) = f(value) && left.forAll(f) && right.forAll(f)
}
