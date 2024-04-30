package edu.austral.dissis.chess.engine.Movement
import edu.austral.dissis.chess.engine.Game.Board
import edu.austral.dissis.chess.engine.Piece.Position
import edu.austral.dissis.chess.engine.Movement.Validator.InBoardValidator
import edu.austral.dissis.chess.engine.Piece.Piece

class DiagonalMovement : Movement {
  private val inBoardValidator = InBoardValidator()
  override fun move(from: Position, to: Position, board: Board): Board {
    val pieceToMove = board.pieces[from]
    val newPosition = Pair(to, pieceToMove)
    val pieceMoved = Pair(from, null)
    val finalPieces = board.pieces + newPosition + pieceMoved
    return Board(board.size, finalPieces, board.deadPieces)
  }
}