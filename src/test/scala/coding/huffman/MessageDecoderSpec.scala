package coding.huffman

import adt.HuffmanEncodingTree.{Branch, HuffmanEncodingTree, Leaf}
import org.scalatest.FlatSpec

class MessageDecoderSpec extends FlatSpec {
  val huffmanTree: HuffmanEncodingTree[String] =
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

  "An encoded message " should "be decoded when it is a valid encoding " in {
    assert(MessageDecoder(huffmanTree).decode(List(
      One, Zero,//C
      One, Zero,//C
      One, Zero,//C
      Zero, One,//B
      Zero, Zero,//A
      Zero, One//B
    )) === Some(List("C", "C", "C", "B", "A", "B")))
  }

  "An encoded message " should "not be decoded when its encoding is bad " in {
    assert(MessageDecoder(huffmanTree).decode(List(
      One, One,
      One, Zero
    )) === None)
  }

  "An empty encoded message " should "return None " in {
    assert(MessageDecoder(huffmanTree).decode(List.empty) === None)
  }
}
