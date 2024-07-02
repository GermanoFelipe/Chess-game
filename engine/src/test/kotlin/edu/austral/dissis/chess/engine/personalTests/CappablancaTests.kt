package edu.austral.dissis.chess.engine.personalTests

import edu.austral.dissis.chess.engine.factory.createCheckMateTest
import edu.austral.dissis.chess.engine.factory.createVariantChess
import edu.austral.dissis.chess.engine.piece.ChessPieceType
import edu.austral.dissis.twoDBoardGame.piece.Color
import edu.austral.dissis.twoDBoardGame.position.Position
import org.junit.jupiter.api.Test

class CappablancaTests {

  var game = createVariantChess()

  @Test
  fun boardTest(): Unit{
    val board = game.getBoard()
    assert(board.getPiece(Position(1, 3))!!.type == ChessPieceType.ARCHBISHOP)
    assert(board.getPiece(Position(8, 8))!!.type == ChessPieceType.CHANCELLOR)
    assert(board.getPiece(Position(8, 3))!!.pieceColor == Color.BLACK
            && (board.getPiece(Position(8, 3))!!.type == ChessPieceType.ARCHBISHOP))
  }

  @Test
  fun testStringType(): Unit {
    val board = game.getBoard()
    assert(board.getPiece(Position(1, 3))!!.type.string() == "archbishop")
    assert(board.getPiece(Position(8, 8))!!.type.string() == "chancellor")
  }

  @Test
  fun testCheckMateBoard(): Unit {
    val game2 = createCheckMateTest()
    val board2 = game2.getBoard()
    assert(board2.getPiece(Position(6, 5))!!.type == ChessPieceType.PAWN)

  }
}