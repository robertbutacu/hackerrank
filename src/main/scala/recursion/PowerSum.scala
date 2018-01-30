package recursion

/*
Find the number of ways that a given integer, , can be expressed as the sum of the  powers of unique, natural numbers.
If X = 10  and  N = 2,
we need to find the number of ways that 10 can be represented as the sum of squares of unique numbers.

10 = 1^2 + 3^2
This is the only way in which  can be expressed as the sum of unique squares.

Variation: do the same for a negative X.
 */

object PowerSum {
  case class Combinations[A: Numeric](total: Option[A], variations: List[List[A]])

  def computePositive[A: Numeric](X: A, N: Int): Combinations[A] = {
    Combinations[A](None, List.empty)
  }

  def compute[A: Numeric](X: A, N: Int): Combinations[A] = {
    Combinations[A](None, List.empty)
  }
}
