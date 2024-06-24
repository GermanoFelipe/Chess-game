package edu.austral.dissis.chess.engine.movement.validator.limits

import edu.austral.dissis.chess.engine.board.DefaultBoard
import edu.austral.dissis.chess.engine.movement.validator.GeneralPieceRules.PieceRuleValidator
import edu.austral.dissis.chess.engine.piece.Position

class UnlimitedValidator: PieceRuleValidator {
  override fun checkMovement(from: Position, to: Position, defaultBoard: DefaultBoard): Boolean {
    return true
  }
}