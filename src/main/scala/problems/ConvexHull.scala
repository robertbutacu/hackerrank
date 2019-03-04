package problems

import eu.timepit.refined._
import eu.timepit.refined.api.Refined
import eu.timepit.refined.auto._
import eu.timepit.refined.collection._

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

  trait PointAlgebra[P] {
    def lowestPoint(points: List[P]): P
    def sortPoints(points: List[P]): List[P]
    def crossProduct(p1: P, p2: P, p3: P): Turn
  }

  object PointAlgebra {
    implicit def twoDimensionAlgebra: PointAlgebra[Point] = new PointAlgebra[Point] {
      override def sortPoints(points: List[Point]): List[Point] = {
       points.filterNot(_ == lowestPoint(points)).sortBy { p =>
          val deltaY = p.Y - lowestPoint(points).Y
          val deltaX = p.X - lowestPoint(points).X
          Math.atan(deltaY / deltaX) * 180 / Math.PI
        }
      }

      override def crossProduct(p1: Point, p2: Point, p3: Point): Turn = {
        // (x2 - x1)(y3 - y1) - (y2 - y1)(x3 - x1)
        (p2.X - p1.X) * (p3.Y - p1.Y) - (p2.Y - p1.Y) * (p3.X - p1.X) match {
          case 0.0                              => Turn.Collinear
          case lessThan0 if lessThan0 < 0       => Turn.Left(lessThan0)
          case greaterThan0 if greaterThan0 > 0 => Turn.Right(greaterThan0)
        }
      }

      override def lowestPoint(points: List[Point]): Point = points.minBy(_.Y)
    }
  }


  trait ConvexHullAlgorithm[P] {
    def getHull(points: NonEmptyList[P]): Either[Error, NonEmptyList[P]]
  }

  implicit def grahamScanAlgorithm[P](implicit pointAlgebra: PointAlgebra[P]): ConvexHullAlgorithm[P] = new ConvexHullAlgorithm[P] {
    override def getHull(points: NonEmptyList[P]): Either[Error, NonEmptyList[P]]= {
      @tailrec
      def go(pointsLeft: List[P], convexHull: List[P]): List[P] = {
        pointsLeft.length match {
          case 0 => convexHull
          case _ =>
            val (p2, p1, p0) = (pointsLeft.head, convexHull.head, convexHull.tail.head)

            pointAlgebra.crossProduct(p2, p1, p0) match {
              case Turn.Right(v)  =>
                println(s"[LOG] Rejecting $p1 with Right Turn cross product. Current convex hull: $convexHull")
                go(pointsLeft, convexHull.tail)
              case Turn.Left(v)   =>
                println(s"[LOG] Accepting $p1 with Left Turn cross product. Current convex hull: $convexHull")
                go(pointsLeft.tail, convexHull.+:(pointsLeft.head))
              case Turn.Collinear =>
                println(s"[LOG] Accepting $p1 with Collinear cross product. Current convex hull: $convexHull")
                go(pointsLeft.tail, convexHull.+:(pointsLeft.head))
            }
        }
      }

      val remainingPoints = pointAlgebra.sortPoints(points)
      if(remainingPoints.length < 3) {
        println("[LOG] Length is not at least 3 - returning initial list")
        Right(points)
      }
      else {
        val convexHull = List(remainingPoints.head, pointAlgebra.lowestPoint(points))
        val remaining = remainingPoints.drop(1)

        refineV[NonEmpty](go(remaining, convexHull)).left.map(_ => InsufficientPoints)
      }
    }
  }
}
