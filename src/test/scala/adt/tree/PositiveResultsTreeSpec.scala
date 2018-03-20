package adt.tree

import org.scalatest.FlatSpec
import scala.Ordering.Int

class PositiveResultsTreeSpec extends FlatSpec {
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

  "Postorder traversal " should " return 4 5 2 6 7 3 1" in {
    assert(tree.postOrder === List(4, 5, 2, 6, 7, 3, 1))
  }

  "Preorder traversal " should " return 4 5 2 6 7 3 1" in {
    assert(tree.preOrder === List(1, 2, 4, 5, 3, 6, 7))
  }

  "Height " should " be 2" in {
    assert(tree.height === 2)
  }

  "Searching for an existing number in a tree" should " return the road to that number" in {
    assert(tree.DFS(5) === Some(List(1, 2, 5)))
    assert(tree.DFS(6) === Some(List(1, 3, 6)))
    assert(tree.DFS(1) === Some(List(1)))
  }

  "Common ancestor traversal " should " return the common ancestor of 2 elements" in {
    assert(tree.commonAncestor(2, 5) === Some(2))
    assert(tree.commonAncestor(4, 5) === Some(2))
    assert(tree.commonAncestor(5, 6) === Some(1))
  }

  "BFS traversal " should "return the Breadth First Search of the tree" in {
    assert(tree.BFS() === List(1, 2, 3, 4, 5, 6, 7))
  }

  "DFS traversal " should "return the depth first search of the tree" in {
    assert(tree.DFS() === List(1, 2, 4, 5, 3, 6, 7))
  }

  "Mapping over a tree" should "return that tree mapped depending on the function " in {
    assert(tree.map(_ + 1) === BinaryTreeBranch(2,
      BinaryTreeBranch(3,
        BinaryTreeBranch(5, BinaryTreeEmpty[Int](), BinaryTreeEmpty[Int]()),
        BinaryTreeBranch(6, BinaryTreeEmpty[Int](), BinaryTreeEmpty[Int]())),
      BinaryTreeBranch(4,
        BinaryTreeBranch(7, BinaryTreeEmpty[Int](), BinaryTreeEmpty[Int]()),
        BinaryTreeBranch(8, BinaryTreeEmpty[Int](), BinaryTreeEmpty[Int]()))
    ))

    assert(tree.map(_.toString()) === BinaryTreeBranch("1",
      BinaryTreeBranch("2",
        BinaryTreeBranch("4", BinaryTreeEmpty[String](), BinaryTreeEmpty[String]()),
        BinaryTreeBranch("5", BinaryTreeEmpty[String](), BinaryTreeEmpty[String]())),
      BinaryTreeBranch("3",
        BinaryTreeBranch("6", BinaryTreeEmpty[String](), BinaryTreeEmpty[String]()),
        BinaryTreeBranch("7", BinaryTreeEmpty[String](), BinaryTreeEmpty[String]()))
    ))
  }

  "Applying forAll over a valid tree" should "return the Boolean value " +
    "enforced by that function" in {
    assert(tree.forAll(_ > 0) === true)
    assert(tree.forAll(_ < 100) === true)
    assert(tree.forAll(_ % 2 == 0) === false)
  }

  "Leveling traversal " should "return the Breadth First Search of the tree" in {
    assert(tree.leveling === List(1, 2, 3, 4, 5, 6, 7))
  }

  "Swapping on a level k" should "return a tree with all nodes swapped on level k" in {
    assert(tree.swap(1) === BinaryTreeBranch(1,
      BinaryTreeBranch(3,
        BinaryTreeBranch(6, BinaryTreeEmpty(), BinaryTreeEmpty()),
        BinaryTreeBranch(7, BinaryTreeEmpty(), BinaryTreeEmpty())),
      BinaryTreeBranch(2,
        BinaryTreeBranch(4, BinaryTreeEmpty(), BinaryTreeEmpty()),
        BinaryTreeBranch(5, BinaryTreeEmpty(), BinaryTreeEmpty()))))

    assert(tree.swap(2) === BinaryTreeBranch(1,
      BinaryTreeBranch(2,
        BinaryTreeBranch(5, BinaryTreeEmpty(), BinaryTreeEmpty()),
        BinaryTreeBranch(4, BinaryTreeEmpty(), BinaryTreeEmpty())),
      BinaryTreeBranch(3,
        BinaryTreeBranch(7, BinaryTreeEmpty(), BinaryTreeEmpty()),
        BinaryTreeBranch(6, BinaryTreeEmpty(), BinaryTreeEmpty()))
    ))
  }

  "Swapping j times on level ks" should "return the tree after swapping j times on ks levels" in {
    assert(tree.swapAll(List(1, 2)) === BinaryTreeBranch(1,
      BinaryTreeBranch(3,
        BinaryTreeBranch(7, BinaryTreeEmpty(), BinaryTreeEmpty()),
        BinaryTreeBranch(6, BinaryTreeEmpty(), BinaryTreeEmpty())),
      BinaryTreeBranch(2,
        BinaryTreeBranch(5, BinaryTreeEmpty(), BinaryTreeEmpty()),
        BinaryTreeBranch(4, BinaryTreeEmpty(), BinaryTreeEmpty()))
    ))
  }

  "Swapping on a non-existent k level " should "return the same tree" in {
    assert(tree.swap(100) === tree)
  }

  "Prunning a tree t by a tree t' " should "return the tree t without that specific t'" in {
    assert(tree.prune(toPrune) === BinaryTreeBranch(1,
      BinaryTreeEmpty(),
      BinaryTreeBranch(3,
        BinaryTreeBranch(6, BinaryTreeEmpty(), BinaryTreeEmpty()),
        BinaryTreeBranch(7, BinaryTreeEmpty(), BinaryTreeEmpty()))
    ))

    assert(tree.prune(tree) === BinaryTreeEmpty())
  }

  "Adding elements to a BST" should "return the tree with the added elements" in {
    assert(new BinaryTreeEmpty[Int].add(1).add(2).add(3) ===
      BinaryTreeBranch(1,
        new BinaryTreeEmpty[Int],
        BinaryTreeBranch(2, new BinaryTreeEmpty[Int], BinaryTreeBranch(3,
          new BinaryTreeEmpty[Int], new BinaryTreeEmpty[Int]
        ))))
  }
}
