package recursion

import org.scalatest.FlatSpec
import recursion.PowerSum.Combinations

class PowerSumSpec extends FlatSpec {
  "Given X = 100 and Y = 3 it" should " return a single combination " in {
    assert(PowerSum.computePositive(100, 3) === Combinations(1, List(List(1, 2, 3, 4))))
  }

  "Given X = 100 and Y = 5" should " return an empty answer " in {
    assert(PowerSum.computePositive(100, 5) === Combinations())
  }
}
