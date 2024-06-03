package edu.austral.dissis.chess.engine.movement.validator.inPath

import edu.austral.dissis.chess.engine.board.DefaultBoard
import edu.austral.dissis.chess.engine.movement.validator.MovementValidator
import edu.austral.dissis.chess.engine.piece.Position

class ColumnNoPieceInPathValidator : MovementValidator {
  override fun checkMovement(from: Position, to: Position, defaultBoard: DefaultBoard): Boolean {
      val min = minOf(from.row, to.row)
      val max = maxOf(from.row, to.row)
      for (i in min until max) {
        if (defaultBoard.pieces[Position(i, from.column)] != null) return false
      }
      return true
  }
}