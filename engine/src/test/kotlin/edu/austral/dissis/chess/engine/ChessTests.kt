package edu.austral.dissis.chess.engine

import edu.austral.dissis.chess.engine.factory.createDefaultBoard
import edu.austral.dissis.chess.engine.factory.createDefaultChess
import edu.austral.dissis.chess.engine.factory.createPawn
import edu.austral.dissis.chess.engine.piece.ChessPieceType
import edu.austral.dissis.twoDBoardGame.board.DefaultBoard
import edu.austral.dissis.twoDBoardGame.board.SizeOfBoard
import edu.austral.dissis.twoDBoardGame.piece.Color
import edu.austral.dissis.twoDBoardGame.piece.Piece
import edu.austral.dissis.twoDBoardGame.position.Position
import edu.austral.dissis.twoDBoardGame.results.UnsuccessfullMovementResult
import edu.austral.dissis.twoDBoardGame.results.SuccessfullMovementResult
import org.junit.jupiter.api.Test

class ChessTests {

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
    assert(result is SuccessfullMovementResult)

    val newTurn = game.getTurnMan().actualTurn()
    assert(newTurn == Color.WHITE)
  }

  @Test
  fun invalidMoveTest(): Unit {
    val result = game.movePiece(Position(2, 1), Position(6, 1))
    assert(result is UnsuccessfullMovementResult)
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

 // @Test
 // fun addAndRemovePieceTest(): Unit{
 //   val map: MutableMap<Position, Piece> = mutableMapOf()
 //   val sizeOfBoard: SizeOfBoard = SizeOfBoard(8,8)
 //   val board = DefaultBoard(sizeOfBoard, map)
//
 //   val piece = createPawn(Color.WHITE)
//
 //   board.addPiece(Position(1,1), piece)
 //   val pieceToCompare = board.getPiece(Position(1,1))
 //   assert(piece.type == pieceToCompare!!.type)
 // }


}