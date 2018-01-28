package Tree

object Example {
  def run(): Unit = {
    val tree: Tree[String] =
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

    println(MessageDecoder(tree).decodeFirstChar(List(One, One)))
    /*
                   ""
              0          1
            ""              ""
           0   1         0     1
          A     B      C

     */
  }
}
