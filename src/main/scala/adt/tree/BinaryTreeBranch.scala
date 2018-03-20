package adt.tree

import scala.annotation.tailrec

case class BinaryTreeBranch[A: Ordering](value: A, left: Tree[A], right: Tree[A]) extends Tree[A] {
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
    List(this.value) ::: this.left.preOrder ::: this.right.preOrder

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
    this.left.inOrder ::: List(this.value) ::: this.right.inOrder

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
    this.left.postOrder ::: this.right.postOrder ::: List(this.value)

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

    go(this, List.empty, List.empty)
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
    val leftHeight = this.left.height + 1
    val rightHeight = this.right.height + 1

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
  override def DFS(): List[A] = List(this.value) ::: this.left.DFS() ::: this.right.DFS()

  /**
    * @param subtree => the subtree that is to be removed from the current tree
    * @return a new tree without the subtree ( if it exists )
    */
  override def prune(subtree: Tree[A]): Tree[A] =
    if (subtree == this) BinaryTreeEmpty()
    else BinaryTreeBranch(this.value,
      left.prune(subtree),
      right.prune(subtree))


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
    * @param el - to be added
    * @return the new tree with the element added
    */
  override def add(el: A): Tree[A] = {
    val n = implicitly[Ordering[A]]

    el match {
      case less if n.lt(el, value) => BinaryTreeBranch(value, left.add(el), right)
      case equal if n.equiv(el, value) => this
      case greater if n.gt(el, value) => BinaryTreeBranch(value, left, right.add(el))
    }
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

    go(this, List.empty)
  }


  /**
    * @param f => function to be applied over all values of the map
    * @tparam B => the new type of the Tree
    * @return => a new tree with f functions applied over all values of the current tree
    */
  override def map[B: Ordering](f: A => B) = BinaryTreeBranch(f(this.value), left.map(f), right.map(f))


  /**
    * @param f => the function returning a boolean which will be applied over all values
    * @return => true if all values of the tree are compliant with f, false otherwise
    */
  override def forAll(f: A => Boolean): Boolean =
    f(value) && left.forAll(f) && right.forAll(f)


  /**
    * @param k => the level where all the subtree will swap its nodes
    * @return => a new Tree where all nodes on k level are swapped
    */
  override def swap(k: Int): Tree[A] = {
    require ( k > 0)

    if (k == 1) BinaryTreeBranch(value, right, left)
    else BinaryTreeBranch(value, left.swap(k - 1), right.swap(k - 1))
  }


  /**
    * @param ks => perform k swaps on current Tree
    * @return => the transformed tree on all k levels
    */
  override def swaps(ks: List[Int]): Tree[A] =
    ks.foldRight[Tree[A]](this) { (curr, res) => res.swap(curr) }


  /**
    * Recursively go deeper into the BST to find the element, if it exists
    *
    * @param f - a function returning a boolean for which an element will need to be found
    * @return an element fulfilling the according function
    */
  override def find(f: A => Boolean): Option[A] =
    if (f(this.value)) Some(this.value)
    else left.find(f).orElse(right.find(f))

  override def filter(f: A => Boolean): List[A] =
    if (f(value))
      List(value) ::: left.filter(f) ::: right.filter(f)
    else
      left.filter(f) ::: right.filter(f)

  override def filterNot(f: A => Boolean): List[A] =
    if(!f(value))
      List(value) ::: left.filterNot(f) ::: right.filterNot(f)
    else
      left.filterNot(f) ::: right.filterNot(f)
}
