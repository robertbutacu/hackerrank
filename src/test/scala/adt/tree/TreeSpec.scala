package adt.tree

import org.scalatest.FlatSpec

class TreeSpec extends FlatSpec {
  val tree: Tree[Int] =
    BinaryTreeBranch(1,
      BinaryTreeBranch(2,
        BinaryTreeBranch(4, BinaryTreeEmpty(), BinaryTreeEmpty()),
        BinaryTreeBranch(5, BinaryTreeEmpty(), BinaryTreeEmpty())),
      BinaryTreeBranch(3,
        BinaryTreeBranch(6, BinaryTreeEmpty(), BinaryTreeEmpty()),
        BinaryTreeBranch(7, BinaryTreeEmpty(), BinaryTreeEmpty()))
    )
  /*
                1

          2            3

       4    5       6    7

       PreOrder: 1 2 4 5 3 6 7
       PostOrder: 4 5 2 6 7 3 1
       InOrder: 4 2 5 1 6 3 7
   */
  val toPrune: Tree[Int] = BinaryTreeBranch(2,
    BinaryTreeBranch(4, BinaryTreeEmpty(), BinaryTreeEmpty()),
    BinaryTreeBranch(5, BinaryTreeEmpty(), BinaryTreeEmpty()))

  "Inorder traversal " should " return 4 2 5 1 6 3 7" in {
    assert(tree.inOrder === List(4, 2, 5, 1, 6, 3, 7))
  }

  "Inorder traversal " should " return an empty list" in {
    assert(BinaryTreeEmpty().inOrder === List())
  }

  "Postorder traversal " should " return 4 5 2 6 7 3 1" in {
    assert(tree.postOrder === List(4, 5, 2, 6, 7, 3, 1))
  }

  "Postorder traversal " should " return an empty list" in {
    assert(BinaryTreeEmpty().postOrder === List())
  }

  "Preorder traversal " should " return 4 5 2 6 7 3 1" in {
    assert(tree.preOrder === List(1, 2, 4, 5, 3, 6, 7))
  }

  "Preorder traversal " should " return an empty list" in {
    assert(BinaryTreeEmpty().preOrder === List())
  }

  "Height " should " be 2" in {
    assert(tree.height === 2)
  }

  "Height in a root-only tree " should " be 0" in {
    assert(BinaryTreeBranch(1, BinaryTreeEmpty(), BinaryTreeEmpty()).height === 0)
  }

  "Height " should " be 0" in {
    assert(BinaryTreeEmpty().height === -1)
  }

  "Searching for an existing number in a tree" should " return the road to that number" in {
    assert(tree.DFS(5) === Some(List(1, 2, 5)))
    assert(tree.DFS(6) === Some(List(1, 3, 6)))
    assert(tree.DFS(1) === Some(List(1)))
  }

  "Searching for a non-existing number in a tree" should " return None" in {
    assert(tree.DFS(12) === None)
    assert(tree.DFS(41) === None)
    assert(tree.DFS(0) === None)
  }

  "Common ancestor traversal " should " return the common ancestor of 2 elements" in {
    assert(tree.commonAncestor(2, 5) === Some(2))
    assert(tree.commonAncestor(4, 5) === Some(2))
    assert(tree.commonAncestor(5, 6) === Some(1))
  }

  "Common ancestor traversal " should
    " return none if there is no common ancestor ( aka at least one element doesnt exist" in {
    assert(tree.commonAncestor(10, 5) === None)
    assert(tree.commonAncestor(0, 0) === None)
    assert(tree.commonAncestor(5, 9) === None)
  }

  //println("BFS: " + tree.BFS())//should be 1 2 3 4 5 6 7
  //println("Adding 1 to elements: " + tree.map(e => e + 1))
  //println("Forall > 1: " + tree.forAll(_ > 0))
  //println("Prunning: " + tree.prune(toPrune))
  //println("Swaps: " + tree.swaps(List(0, 1, 2)))
  //println("Leveling: " + tree.leveling) //should be 1 2 3 4 5 6 7

  /*"Postorder traversal " should " return 4 5 2 6 7 3 1" in {
    assert(tree.postOrder === List(4, 5, 2, 6, 7, 3, 1))
  }

  "Postorder traversal " should " return 4 5 2 6 7 3 1" in {
    assert(tree.postOrder === List(4, 5, 2, 6, 7, 3, 1))
  }*/
}
