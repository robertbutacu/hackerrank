package Tree

case class Decoded[Option[_]](decoding: Option[_], remainingMessage: List[Encoded])
