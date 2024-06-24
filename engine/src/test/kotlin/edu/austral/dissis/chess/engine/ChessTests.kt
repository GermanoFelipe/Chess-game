package edu.austral.dissis.chess.engine

import edu.austral.dissis.chess.engine.board.DefaultBoard
import edu.austral.dissis.chess.engine.game.Game
import edu.austral.dissis.chess.engine.game.TurnDefault
import edu.austral.dissis.chess.engine.movement.Movement
import edu.austral.dissis.chess.engine.movement.piecesMovRules.DefaultMovementRules
import edu.austral.dissis.chess.engine.piece.ChessPieceType
import edu.austral.dissis.chess.engine.piece.Color
import edu.austral.dissis.chess.engine.piece.Piece
import edu.austral.dissis.chess.engine.piece.Position
import edu.austral.dissis.chess.engine.rules.ChessRuleManager
import org.junit.jupiter.api.Test

class ChessTests {

  val board = DefaultBoard(8,8, pieceCreator())

  private val turn = TurnDefault(Color.WHITE)

  val rules = ChessRuleManager()

  var game = Game(board, turn, mapOf<Piece, List<Movement>>(), rules)

  fun pieceCreator(): Map<Position, Piece> {
    val pieces = HashMap<Position, Piece>()

    for (i in 1..8){
      pieces[Position(2, i)] = Piece(ChessPieceType.PAWN, Color.WHITE, false, ChessPieceType.PAWN.string, DefaultMovementRules().createPawnRules())
      pieces[Position(7, i)] = Piece(ChessPieceType.PAWN, Color.BLACK, false, ChessPieceType.PAWN.string, DefaultMovementRules().createPawnRules())
    }

    pieces[Position(1, 1)] = Piece(ChessPieceType.ROOK, Color.WHITE, false, ChessPieceType.ROOK.string, DefaultMovementRules().createRookRules())
    pieces[Position(1, 8)] = Piece(ChessPieceType.ROOK, Color.WHITE, false, ChessPieceType.ROOK.string, DefaultMovementRules().createRookRules())
    pieces[Position(8, 1)] = Piece(ChessPieceType.ROOK, Color.BLACK, false, ChessPieceType.ROOK.string, DefaultMovementRules().createRookRules())
    pieces[Position(8, 8)] = Piece(ChessPieceType.ROOK, Color.BLACK, false, ChessPieceType.ROOK.string, DefaultMovementRules().createRookRules())

    pieces[Position(1, 2)] = Piece(ChessPieceType.KNIGHT, Color.WHITE, false, ChessPieceType.KNIGHT.string, DefaultMovementRules().createKnightRules())
    pieces[Position(1, 7)] = Piece(ChessPieceType.KNIGHT, Color.WHITE, false, ChessPieceType.KNIGHT.string, DefaultMovementRules().createKnightRules())
    pieces[Position(8, 2)] = Piece(ChessPieceType.KNIGHT, Color.BLACK, false, ChessPieceType.KNIGHT.string, DefaultMovementRules().createKnightRules())
    pieces[Position(8, 7)] = Piece(ChessPieceType.KNIGHT, Color.BLACK, false, ChessPieceType.KNIGHT.string, DefaultMovementRules().createKnightRules())

    pieces[Position(1, 3)] = Piece(ChessPieceType.BISHOP, Color.WHITE, false, ChessPieceType.BISHOP.string, DefaultMovementRules().createBishopRules())
    pieces[Position(1, 6)] = Piece(ChessPieceType.BISHOP, Color.WHITE, false, ChessPieceType.BISHOP.string, DefaultMovementRules().createBishopRules())
    pieces[Position(8, 3)] = Piece(ChessPieceType.BISHOP, Color.BLACK, false, ChessPieceType.BISHOP.string, DefaultMovementRules().createBishopRules())
    pieces[Position(8, 6)] = Piece(ChessPieceType.BISHOP, Color.BLACK, false, ChessPieceType.BISHOP.string, DefaultMovementRules().createBishopRules())

    pieces[Position(1, 4)] = Piece(ChessPieceType.QUEEN, Color.WHITE, false, ChessPieceType.QUEEN.string, DefaultMovementRules().createQueenRules())
    pieces[Position(8, 4)] = Piece(ChessPieceType.QUEEN, Color.BLACK, false, ChessPieceType.QUEEN.string, DefaultMovementRules().createQueenRules())

    pieces[Position(1, 5)] = Piece(ChessPieceType.KING, Color.WHITE, false, ChessPieceType.KING.string, DefaultMovementRules().createKingRules())
    pieces[Position(8, 5)] = Piece(ChessPieceType.KING, Color.BLACK, false, ChessPieceType.KING.string, DefaultMovementRules().createKingRules())

    return pieces
  }

  @Test
  fun getPieceTest():Unit{
    val piece = game.board.getPiece(Position(1, 1))
    assert(piece != null)
    assert(piece!!.type == ChessPieceType.ROOK)
    assert(piece.pieceColor == Color.WHITE)
    assert(piece.hasMoveed == false)
    assert(piece.id == ChessPieceType.ROOK.string)
    //assert(piece.pieceRules == DefaultMovementRules().createRookRules())
  }

  @Test
  fun movePieceTest(): Unit{
    val piece = game.board.getPiece(Position(2, 1))
    assert(piece!!.type == ChessPieceType.PAWN)

    val result = game.movePiece(Position(2, 1), Position(3, 1))
    val newGame = (result as edu.austral.dissis.chess.engine.game.results.Valid).game
    assert(newGame.board.getPiece(Position(3, 1))!!.type == ChessPieceType.PAWN)
    assert(newGame.board.getPiece(Position(2, 1)) == null)
    assert(newGame.turn.actualTurn() == Color.BLACK)
  }

  @Test
  fun invalidMoveTest(): Unit{
    val result = game.movePiece(Position(2, 1), Position(6, 1))
    assert(result is edu.austral.dissis.chess.engine.game.results.InvalidMovement)
  }

}