package tree.huffman.coding

case class Decoded[A](decoding: Option[A], remainingMessage: List[Encoded])
