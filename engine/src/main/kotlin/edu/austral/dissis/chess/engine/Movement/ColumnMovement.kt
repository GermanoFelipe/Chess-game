package edu.austral.dissis.chess.engine.Movement

import edu.austral.dissis.chess.engine.Game.Board
import edu.austral.dissis.chess.engine.Piece.Position

class ColumnMovement : Movement {

  override fun move(from: Position, to: Position, board: Board): Board {
    val pieceToMove = board.pieces[from]
    val newPosition = Pair(to, pieceToMove)
    val pieceMoved = Pair(from, null)
    val finalPieces = board.pieces + newPosition + pieceMoved
    return Board(board.size, finalPieces, board.deadPieces)
  }
}