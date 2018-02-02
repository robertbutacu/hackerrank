package Runners

import adt.HuffmanEncodingTree.{Branch, HuffmanEncodingTree, Leaf}
import adt.tree.{BinaryTreeBranch, BinaryTreeEmpty, Tree}
import coding.huffman.{MessageDecoder, One, Zero}

object Runner {
  def runTraversals(): Unit = {

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
