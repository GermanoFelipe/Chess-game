package edu.austral.dissis.chess.engine.movement.validator.directions

import edu.austral.dissis.chess.engine.board.DefaultBoard
import edu.austral.dissis.chess.engine.movement.validator.GeneralPieceRules.PieceRuleValidator
import edu.austral.dissis.twoDBoardGame.position.Position
import kotlin.math.absoluteValue

class DiagonalDirectionValidator: PieceRuleValidator {
  override fun checkMovement(from: Position, to: Position, defaultBoard: DefaultBoard): Boolean {
    return from.row != to.row && from.column != to.column &&
            (from.row - to.row).absoluteValue == (from.column - to.column).absoluteValue
  }
}