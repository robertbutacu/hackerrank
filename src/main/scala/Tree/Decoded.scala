package Tree

case class Decoded[A](decoding: Option[A], remainingMessage: List[Encoded])
