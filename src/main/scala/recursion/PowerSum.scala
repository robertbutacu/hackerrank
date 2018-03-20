package recursion

/*
Difficulty: Medium

Find the number of ways that a given integer, , can be expressed as the sum of the  powers of unique, natural numbers.
If X = 10  and  N = 2,
we need to find the number of ways that 10 can be represented as the sum of squares of unique numbers.

10 = 1^2 + 3^2
This is the only way in which  can be expressed as the sum of unique squares.

Variation: do the same for a negative X.
 */

object PowerSum {

  case class Combinations(total: Int = 0, variations: List[List[Int]] = List.empty) {
    def add(other: Combinations): Combinations = {
      Combinations(this.total + other.total,
        this.variations ::: other.variations)
    }
  }

  def computePositive(X: Int, N: Int): Combinations = {
    require(X > 0)

    def go(remainingSum: Int, curr: Int, road: List[Int]): Option[Combinations] = {
      val currValue = Math.pow(curr, N).toInt

      val currIteration = remainingSum - currValue

      currIteration match {
        case _ if currIteration < 0 => None
        case 0 => Some(Combinations(1, List(road :+ curr)))
        case _ =>
          val nextIterationsCurrValues = ((curr + 1) to (remainingSum - currValue)).toList

          val nextIterationsResults = for {
            next <- nextIterationsCurrValues
            possibleSolution <- go(currIteration, next, road :+ curr)
          } yield possibleSolution

          Some(
            nextIterationsResults.foldRight(Combinations())((acc, total) => total.add(acc))
          )
      }
    }

    (1 to X).map(e => go(X, e, List.empty))
      .toList
      .foldRight(Combinations())((acc, total) => acc.map(total.add).getOrElse(Combinations()))
  }



  //TODO fix this
  def compute(X: Int, N: Int): Combinations = {

    def negative = Math.min(-X, X)

    def positive = Math.max(-X, X)

    def go(remainingSum: Int, curr: Int, road: List[Int], result: List[List[Int]]): Option[Combinations] = {
      val currValue = Math.pow(curr, N).toInt

      val currIteration = remainingSum + Math.min(currValue, -currValue)
      println(remainingSum + " + " + currValue + " = " + currIteration)

      val currResult = if (currIteration == 0) result :+ (road :+ currIteration) else result

      val nextIterationsCurrValues = ((curr + 1) to Math.max(-X, X)).toList

      val nextIterationsResults = for {
        next <- nextIterationsCurrValues
        possibleSolution <- go(currIteration, next, road :+ curr, currResult)
      } yield possibleSolution

      Some(
        nextIterationsResults.foldRight(Combinations())((acc, total) => total.add(acc))
      )
    }

    (negative to positive)
      .map { e => go(X, e, List.empty, List.empty) }
      .foldRight(Combinations())((acc, total) => acc.map(total.add).getOrElse(Combinations()))
  }
}
