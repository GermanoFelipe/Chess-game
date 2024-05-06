package edu.austral.dissis.chess.engine.movement.validator

import edu.austral.dissis.chess.engine.board.Board
import edu.austral.dissis.chess.engine.piece.Position

class LimitValidator(val limit: Int): MovementValidator {
  override fun checkMovement(from: Position, to: Position, board: Board): Boolean {
    val absMoveColumn = from.column - to.column
    val absMoveRow = from.row - to.row
    return absMoveColumn <= limit && absMoveRow <= limit
  }
}