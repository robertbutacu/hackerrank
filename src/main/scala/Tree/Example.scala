package Tree

object Example {
  def run() = {
    val tree: Tree[String] =
      Node(
        Node(
          Leaf("A"),
          Leaf("B")
        ),
        Node(
          Leaf("C"),
          Empty
        )
      )

    println(tree)
    /*
                   ""
              0          1
            ""              ""
           0   1         0     1
          A     B      C

     */
  }
}
