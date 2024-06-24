package edu.austral.dissis.chess.engine

import edu.austral.dissis.chess.engine.factory.createDefaultBoard
import edu.austral.dissis.chess.engine.factory.createDefaultChess
import edu.austral.dissis.twoDBoardGame.game.Game
import edu.austral.dissis.chess.engine.game.TurnDefault
import edu.austral.dissis.twoDBoardGame.game.Movement
import edu.austral.dissis.chess.engine.piece.ChessPieceType
import edu.austral.dissis.chess.engine.winCondition.CheckMate
import edu.austral.dissis.twoDBoardGame.game.mover.DefaultMovApplier
import edu.austral.dissis.twoDBoardGame.piece.Color
import edu.austral.dissis.twoDBoardGame.piece.Piece
import edu.austral.dissis.twoDBoardGame.position.Position
import edu.austral.dissis.twoDBoardGame.results.Invalid
import edu.austral.dissis.twoDBoardGame.results.InvalidMovement
import edu.austral.dissis.twoDBoardGame.results.Valid
import edu.austral.dissis.twoDBoardGame.results.ValidMovement
import edu.austral.dissis.twoDBoardGame.rules.RuleManager
import org.junit.jupiter.api.Test

class ChessTests {

  var game = createDefaultChess()


  @Test
  fun getPieceTest():Unit{
    val piece = game.board.getPiece(Position(1, 1))
    assert(piece != null)
    assert(piece!!.type == ChessPieceType.ROOK)
    assert(piece.pieceColor == Color.WHITE)
    assert(piece.hasMoved == false)
    assert(piece.getId() == ChessPieceType.ROOK.string())
    //assert(piece.pieceRules == DefaultMovementRules().createRookRules())
  }

  @Test
  fun movePieceTest(): Unit{
    val piece = game.board.getPiece(Position(2, 1))
    assert(piece!!.type == ChessPieceType.PAWN)

    val result = game.movePiece(Position(2, 1), Position(3, 1))
    val newGame = (result as ValidMovement).game
    assert(newGame.board.getPiece(Position(3, 1))!!.type == ChessPieceType.PAWN)
    assert(newGame.board.getPiece(Position(2, 1)) == null)
    assert(newGame.turn.actualTurn() == Color.BLACK)
  }

  @Test
  fun invalidMoveTest(): Unit{
    val result = game.movePiece(Position(2, 1), Position(6, 1))
    assert(result is InvalidMovement)
  }

  @Test
  fun boardCraeatorTest(): Unit{
    val board = createDefaultBoard()
    assert(board.getPiece(Position(1, 1))!!.type == ChessPieceType.ROOK)
    assert(board.getPiece(Position(1, 1))!!.pieceColor == Color.WHITE)
    assert(board.getPiece(Position(1, 1))!!.hasMoved == false)
    assert(board.getPiece(Position(1, 1))!!.getId() == ChessPieceType.ROOK.string())

    assert(board.getPiece(Position(2, 1))!!.type == ChessPieceType.PAWN)
  }

}