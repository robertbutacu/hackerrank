package tree

import tree.adt.HuffmanEncodingTree.{Branch, HuffmanEncodingTree, Leaf}
import tree.adt.tree.{Empty, Tree, TreeBranch}
import tree.huffman.coding.{MessageDecoder, One, Zero}

object Runner {
  def runTraversals(): Unit = {
    val tree: Tree[Int] =
      TreeBranch(1,
        TreeBranch(2,
          TreeBranch(4, Empty(), Empty()),
          TreeBranch(5, Empty(), Empty())),
        TreeBranch(3,
          TreeBranch(6, Empty(), Empty()),
          TreeBranch(7, Empty(), Empty()))
      )
    /*
                  1

            2            3

         4    5       6    7

         PreOrder: 1 2 4 5 3 6 7
         PostOrder: 4 5 2 6 7 3 1
         InOrder: 4 2 5 1 6 3 7
     */

    //println("Inorder: " + tree.inOrder)
    //println("Postorder: " + tree.postOrder)
    //println("Preorder: " + tree.preOrder)
    //println("Height: " + tree.height)//should be 2
    //println("Searching for 5: " + tree.DFS(6) )// should be 1 2 5
    //println("Common ancestor: " + tree.commonAncestor(4, 9))
    //println("BFS: " + tree.BFS())//should be 1 2 3 4 5 6 7
    //println("Adding 1 to elements: " + tree.map(e => e + 1))
    println("Forall > 1: " + tree.forAll(_ > 0))
  }

  def runDecoding(): Unit = {
    val tree: HuffmanEncodingTree[String] =
      Branch(
        Branch(
          Leaf(Some("A")),
          Leaf(Some("B"))
        ),
        Branch(
          Leaf(Some("C")),
          Leaf()
        )
      )

    println(MessageDecoder(tree).decode(List(
      One, Zero,//C
      One, Zero,//C
      One, Zero,//C
      Zero, One,//B
      Zero, Zero,//A
      Zero, One//B
    )))
    /*
                   ""
              0          1
            ""              ""
           0   1         0     1
          A     B      C

     */
  }
}
