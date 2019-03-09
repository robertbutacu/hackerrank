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
    def generateBoard: Board = Board((0 to 6).map(_ => (0 to 6).map(_ => Free).toList).toList)

    def updateBoard(board: Board, X: Int, Y: Int): Board = {
      if (Board.isFreeSpot(board, X, Y)) {
        val boardUpdatedVertically = xMovesHorizontally(X)
        ???
      }
      else ???
    }

    def isFreeSpot(board: Board, X: Int, Y: Int): Boolean = {
      def isValidParameter(X: Int, Y: Int): Boolean = X >= 0 && X <= 6 && Y >= 0 && Y <= 6

      def freeDiagonally: Boolean = {
        xMovesHorizontally(X).forall{ x =>
          yMovesVertically(Y).forall {
            y => isValidParameter(x, y) && board.pieces(y)(x).isFree()
          }
        }
      }

      def freeHorizontally: Boolean = {
        xMovesHorizontally(X).forall(x => isValidParameter(x, Y) && board.pieces(Y)(x).isFree())
      }

      def freeVertically: Boolean = {
        yMovesVertically(Y).forall(y => isValidParameter(X, y) && board.pieces(y)(X).isFree())
      }

      def freeKnightMove: Boolean = {
        {for {
          x <- xKnightMoves(X)
          y <- yKnightMoves(Y)
          if isValidParameter(x, y)
          if board.pieces(y)(x).isFree()
        } yield (x, y)}.isEmpty
      }
      def freePosition: Boolean = board.pieces(Y)(X).isFree()

      freePosition && freeDiagonally && freeHorizontally && freeKnightMove && freeVertically
    }

    def xMovesHorizontally(X: Int): List[Int] = (X - 1 to 0 by -1).toList ::: (X + 1 to 6).toList
    def yMovesVertically(Y: Int): List[Int]   = (Y - 1 to 0 by -1).toList ::: (Y + 1 to 6).toList
    def xKnightMoves(X: Int): List[Int] = List(X + 2, X - 2)
    def yKnightMoves(Y: Int): List[Int] = List(Y + 1, Y - 1)

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
