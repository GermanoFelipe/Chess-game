package edu.austral.dissis.chess.engine.movement.validator.limits

import edu.austral.dissis.chess.engine.board.DefaultBoard
import edu.austral.dissis.chess.engine.movement.validator.GeneralPieceRules.PieceRuleValidator
import edu.austral.dissis.chess.engine.piece.Position
import java.lang.Math.abs

class LimitValidator(
                    val limit: Int
                    ): PieceRuleValidator {
  override fun checkMovement(from: Position, to: Position, defaultBoard: DefaultBoard): Boolean {
    val absMoveColumn = abs (from.column - to.column)
    val absMoveRow = abs (from.row - to.row)
    return absMoveColumn <= limit && absMoveRow <= limit
  }
}