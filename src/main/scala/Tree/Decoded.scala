package Tree

case class Decoded[+A](decoding: A, remainingMessage: List[Encoded])
