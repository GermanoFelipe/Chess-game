package edu.austral.dissis.chess.engine.personalTests

import edu.austral.dissis.chess.engine.factory.createDefaultBoard
import edu.austral.dissis.chess.engine.factory.createDefaultChess
import edu.austral.dissis.chess.engine.piece.ChessPieceType
import edu.austral.dissis.twoDBoardGame.piece.Color
import edu.austral.dissis.twoDBoardGame.position.Position
import edu.austral.dissis.twoDBoardGame.results.UnsuccessfulMovementResult
import edu.austral.dissis.twoDBoardGame.results.SuccessfulMovementResult
import org.junit.jupiter.api.Test

class DefaultChessTests {

  var game = createDefaultChess()

  @Test
  fun getPieceTest(): Unit {
    val piece = game.getBoard().getPiece(Position(1, 1))
    assert(piece != null)
    assert(piece!!.type == ChessPieceType.ROOK)
    assert(piece.pieceColor == Color.WHITE)
    assert(!piece.hasMoved)
  }

  @Test
  fun movePieceTest(): Unit {
    val piece = game.getBoard().getPiece(Position(2, 1))
    assert(piece!!.type == ChessPieceType.PAWN)

    val result = game.movePiece(Position(2, 1), Position(3, 1))
    assert(result is SuccessfulMovementResult)

    val newTurn = game.getTurnMan().actualTurn()
    assert(newTurn == Color.WHITE)
  }

  @Test
  fun invalidMoveTest(): Unit {
    val result = game.movePiece(Position(2, 1), Position(6, 1))
    assert(result is UnsuccessfulMovementResult)
  }

  @Test
  fun boardCraeatorTest(): Unit {
    val board = createDefaultBoard()
    assert(board.getPiece(Position(1, 1))!!.type == ChessPieceType.ROOK)
    assert(board.getPiece(Position(1, 1))!!.pieceColor == Color.WHITE)
    assert(!board.getPiece(Position(1, 1))!!.hasMoved)
    assert(board.getPiece(Position(2, 1))!!.type == ChessPieceType.PAWN)
  }

  @Test
  fun getPiecesTest(): Unit {
    val pieces = game.getBoard().getPieces()
    assert(pieces.size == 32)
  }
}