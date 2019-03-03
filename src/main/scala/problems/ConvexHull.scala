package problems

import eu.timepit.refined.api.Refined
import eu.timepit.refined.collection.NonEmpty
import eu.timepit.refined._

import scala.annotation.tailrec

object ConvexHull extends App {
  type NonEmptyList[A] = List[A] Refined NonEmpty

  case class Point(X: Double, Y: Double)

  trait Turn {
    def value: Double
  }

  object Turn {
    case class  Left(value: Double)  extends Turn
    case class  Right(value: Double) extends Turn
    case object Collinear                extends Turn {
      override def value: Double = 0.0
    }
  }

  trait Error
  case object InsufficientPoints extends Error
  case object NoPoints           extends Error


  trait ConvexHullAlgorithm {
    def getHull(points: NonEmptyList[Point]): Either[Error, NonEmptyList[Point]]
  }

  implicit def grahamScanAlgorithm: ConvexHullAlgorithm = new ConvexHullAlgorithm {
    override def getHull(points: NonEmptyList[Point]): Either[Error, NonEmptyList[Point]]= {
      val pointsList = points.value

      val lowestYPoint: Point = pointsList.minBy(_.Y)

      def sortPoints(): List[Point] = pointsList.filter(_ == lowestYPoint).sortBy { p =>
        val deltaY = p.Y - lowestYPoint.Y
        val deltaX = p.X - lowestYPoint.X
        Math.atan(deltaY / deltaX) * 180 / Math.PI
      }

      def crossProduct(p1: Point, p2: Point, p3: Point): Turn = {
        // (x2 - x1)(y3 - y1) - (y2 - y1)(x3 - x1)
        (p2.X - p1.X) * (p3.Y - p1.Y) - (p2.Y - p1.Y)(p3.X - p1.X) match {
          case 0.0                              => Turn.Collinear
          case lessThan0 if lessThan0 < 0       => Turn.Left(lessThan0)
          case greaterThan0 if greaterThan0 > 0 => Turn.Right(greaterThan0)
        }
      }

      @tailrec
      def go(pointsLeft: List[Point], convexHull: List[Point]): List[Point] = {
        pointsLeft.length match {
          case 0                => convexHull
          case (_: Point) ::: _ =>
            val (p2, p1, p0) = (pointsLeft.head, convexHull.head, convexHull.tail.head)

            crossProduct(p2, p1, p0) match {
              case Turn.Right(v)  =>
                println(s"[LOG] Rejecting $p1 with Right Turn cross product")
                go(pointsLeft, convexHull.tail)
              case Turn.Left(v)   =>
                println(s"[LOG] Accepting $p1 with Left Turn cross product")
                go(pointsLeft.tail, convexHull :+ pointsLeft.head)
              case Turn.Collinear =>
                println(s"[LOG] Accepting $p1 with Collinear cross product")
                go(pointsLeft.tail, convexHull :+ pointsLeft.head)
            }
        }
      }

      val remainingPoints = sortPoints()
      if(remainingPoints.length < 3) Right(points)
      else {
        val convexHull = List(remainingPoints.tail.head, remainingPoints.head, lowestYPoint)
        val remaining = remainingPoints.drop(2)

        refineV[NonEmpty](go(remaining, convexHull)).left.map(_ => InsufficientPoints)
      }
    }
  }
}
