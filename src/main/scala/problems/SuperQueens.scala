package problems

import scala.annotation.tailrec

object SuperQueens {
  trait PositionStatus {
    def isFree(positionStatus: PositionStatus): Boolean = {
      this match {
        case QueenOccupied => false
        case QueenBlocked  => false
        case Free          => true
      }
    }
  }

  case object QueenOccupied extends PositionStatus
  case object QueenBlocked  extends PositionStatus
  case object Free          extends PositionStatus

  case class Board(pieces: List[List[PositionStatus]])
  object Board {
    def generateBoard: Board = Board((0 to 6).map(i => (0 to 6).map(_ => Free).toList).toList)

    def isFreeSpot(board: Board, X: Int, Y: Int): Boolean = {
      def freeDiagonally: Boolean = ???
      def freeVertically: Boolean = ???
      def freeKnightMove: Boolean = ???
      def freePosition: Boolean   = ???
      ???
    }
  }


  def generateAll(count: Int): List[Board] = {
    @tailrec
    def go(currentCount: Int, currentX: Int, currentY: Int, board: Board, resultSoFar: Set[Board]): Set[Board] = {
      if(currentX == 7) resultSoFar
      else {
        ???
      }
    }
  }


}
