package edu.austral.dissis.chess.engine.movement.validator.limits

import edu.austral.dissis.chess.engine.board.DefaultBoard
import edu.austral.dissis.chess.engine.movement.validator.MovementValidator
import edu.austral.dissis.chess.engine.piece.Position

class LimitValidator(val limit: Int): MovementValidator {
  override fun checkMovement(from: Position, to: Position, defaultBoard: DefaultBoard): Boolean {
    val absMoveColumn = from.column - to.column
    val absMoveRow = from.row - to.row
    return absMoveColumn <= limit && absMoveRow <= limit
  }
}