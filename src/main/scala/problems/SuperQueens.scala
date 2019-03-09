package problems

import scala.annotation.tailrec

object SuperQueens {
  trait PositionStatus {
    def isFree(): Boolean = {
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
      def isValidParameter(X: Int, Y: Int): Boolean = X >= 0 && X <= 6 && Y >= 0 && Y <= 6

      def freeDiagonally: Boolean = {
        val xMoves = (X - 1 to 0 by -1).toList ::: (X + 1 to 6).toList
        val yMoves = (Y - 1 to 0 by -1).toList ::: (Y + 1 to 6).toList

        {for {
            x <- xMoves
            y <- yMoves
            if isValidParameter(x, y)
            if board.pieces(y)(x).isFree()
          } yield (x, y)}.isEmpty
      }

      def freeVertically: Boolean = {
        val left = (X - 1 to 0 by -1).toList
        val right = (X + 1 to 6).toList
        val xMoves = left ::: right

        {for {
          x <- xMoves
          if isValidParameter(x, Y)
          if board.pieces(Y)(x).isFree()
        } yield x}.isEmpty
      }

      def freeKnightMove: Boolean = {
        val xMoves = List(X + 2, X - 2)
        val yMoves = List(Y + 1, Y - 1)

        {for {
          x <- xMoves
          y <- yMoves
          if isValidParameter(x, y)
          if board.pieces(y)(x).isFree()
        } yield (x, y)}.isEmpty
      }
      def freePosition: Boolean = board.pieces(Y)(X).isFree()

      freePosition && freeDiagonally && freeVertically && freeKnightMove
    }
  }


  def generateAll(count: Int): Set[Board] = {
   // @tailrec
    def go(currentCount: Int, currentX: Int, currentY: Int, board: Board, resultSoFar: Set[Board]): Set[Board] = {
      if(currentX == 7) resultSoFar
      else {
        ???
      }
    }

    ???
  }


}
