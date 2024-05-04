package edu.austral.dissis.chess.engine.movement.validator

import edu.austral.dissis.chess.engine.board.Board
import edu.austral.dissis.chess.engine.piece.Position

class RowNoPieceInPathValidator : MovementValidator {
  override fun checkMovement(from: Position, to: Position, board: Board): Boolean {
    val min = minOf(from.column, to.column)
    val max = maxOf(from.column, to.column)
    for (i in min until max) {
      if (board.pieces[Position(i, from.row)] != null) return false
    }
    return true
  }
}