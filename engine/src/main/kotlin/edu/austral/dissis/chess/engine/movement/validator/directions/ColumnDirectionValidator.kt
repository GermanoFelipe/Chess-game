package edu.austral.dissis.chess.engine.movement.validator.directions

import edu.austral.dissis.chess.engine.board.DefaultBoard
import edu.austral.dissis.chess.engine.movement.validator.MovementValidator
import edu.austral.dissis.chess.engine.piece.Position

class ColumnDirectionValidator: MovementValidator {
  override fun checkMovement(from: Position, to: Position, defaultBoard: DefaultBoard): Boolean {
    return from.row == to.row && from.column != to.column
  }
}