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

    println(MessageDecoder(tree).decode(List(One, Zero)))
    /*
                   ""
              0          1
            ""              ""
           0   1         0     1
          A     B      C

     */
  }
}
