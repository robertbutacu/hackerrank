package problems

import eu.timepit.refined.collection.NonEmpty
import eu.timepit.refined.refineV
import org.scalatest.FlatSpec
import problems.ConvexHull.{Error, NonEmptyList, Point, grahamScanAlgorithm}

class ConvexHullSpec extends FlatSpec {
  "Given a list " should "find the convex hull " in {
    val points: Either[String, NonEmptyList[Point]] = refineV[NonEmpty](List(Point(1.0, 1.0), Point(2.0, 5.0), Point(3.0, 3.0), Point(5.0, 3.0), Point(3.0, 2.0),
      Point(2.0, 2.0)))

    val convexHull = points.flatMap(l => grahamScanAlgorithm.getHull(l))
    val expectedConvexHull = List(Point(1.0, 1.0), Point(2.0, 5.0), Point(5.0, 3.0))

    assert(convexHull.forall(list => list.value.forall(p => expectedConvexHull.contains(p))))
  }
}
