package edu.austral.dissis.chess.engine.personalTests

import edu.austral.dissis.checkers.factory.DefaultCheckersGame
import edu.austral.dissis.checkers.factory.createEatAllCheckers
import edu.austral.dissis.checkers.factory.createKingTests
import edu.austral.dissis.checkers.piece.CheckersPieceType
import edu.austral.dissis.twoDBoardGame.piece.Color
import edu.austral.dissis.twoDBoardGame.position.Position
import edu.austral.dissis.twoDBoardGame.results.SuccessfulMovementResult
import edu.austral.dissis.twoDBoardGame.results.UnsuccessfulMovementResult
import org.junit.jupiter.api.Test

class CheckersTests {

  var checkers = DefaultCheckersGame()

  @Test
  fun getPieceTest(): Unit {
    val piece = checkers.getBoard().getPiece(Position(1, 2))
    assert(piece != null)
    assert(piece!!.type == CheckersPieceType.MAN)
    assert(piece.pieceColor == Color.WHITE)
    assert(!piece.hasMoved)
    assert(piece.type.string() == "pawn")
  }

  @Test
  fun movePieceTest(): Unit {
    val piece = checkers.getBoard().getPiece(Position(3, 2))
    assert(piece!!.type == CheckersPieceType.MAN)

    val result = checkers.movePiece(Position(3, 2), Position(4, 3))
    assert(result is SuccessfulMovementResult)

    val newTurn = checkers.getTurnMan().actualTurn()
    assert(newTurn == Color.WHITE)
  }

  @Test
  fun invalidMoveTest(): Unit {
    val result = checkers.movePiece(Position(3, 2), Position(8, 1))
    assert(result is UnsuccessfulMovementResult)
  }

  @Test
  fun doubleMovement(): Unit{
    val newGame = createEatAllCheckers()

    val result = newGame.movePiece(Position(1, 1), Position(3, 3))
    assert(result is SuccessfulMovementResult)
    val result2 = newGame.movePiece(Position(3, 3), Position(5, 5))
  }

  @Test
  fun kingTester(): Unit{
    val kingTester = createKingTests()

    val result = kingTester.movePiece(Position(6, 6), Position(4, 4))
    assert(result is SuccessfulMovementResult)
  }
}