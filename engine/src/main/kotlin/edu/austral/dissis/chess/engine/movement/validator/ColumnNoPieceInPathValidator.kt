package edu.austral.dissis.chess.engine.movement.validator

import edu.austral.dissis.chess.engine.board.Board
import edu.austral.dissis.chess.engine.piece.Position

class ColumnNoPieceInPathValidator : MovementValidator {
  override fun checkMovement(from: Position, to: Position, board: Board): Boolean {
      val min = minOf(from.row, to.row)
      val max = maxOf(from.row, to.row)
      for (i in min until max) {
        if (board.pieces[Position(i, from.column)] != null) return false
      }
      return true
  }
}