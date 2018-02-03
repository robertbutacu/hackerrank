package adt.tree

import org.scalatest.FlatSpec

class NegativeResultsTreeSpec extends FlatSpec {
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


  "Inorder traversal " should " return an empty list" in {
    assert(BinaryTreeEmpty().inOrder === List())
  }

  "Postorder traversal " should " return an empty list" in {
    assert(BinaryTreeEmpty().postOrder === List())
  }

  "Preorder traversal " should " return an empty list" in {
    assert(BinaryTreeEmpty().preOrder === List())
  }

  "Height in a root-only tree " should " be 0" in {
    assert(BinaryTreeBranch(1, BinaryTreeEmpty(), BinaryTreeEmpty()).height === 0)
  }

  "Height " should " be 0" in {
    assert(BinaryTreeEmpty().height === -1)
  }

  "Searching for a non-existing number in a tree" should " return None" in {
    assert(tree.DFS(12) === None)
    assert(tree.DFS(41) === None)
    assert(tree.DFS(0) === None)
  }

  "Common ancestor traversal " should
    " return none if there is no common ancestor ( aka at least one element doesnt exist" in {
    assert(tree.commonAncestor(10, 5) === None)
    assert(tree.commonAncestor(0, 0) === None)
    assert(tree.commonAncestor(5, 9) === None)
  }

}
