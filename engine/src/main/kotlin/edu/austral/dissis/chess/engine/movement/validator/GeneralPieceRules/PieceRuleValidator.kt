package edu.austral.dissis.chess.engine.movement.validator.GeneralPieceRules

import edu.austral.dissis.chess.engine.game.results.ValidationResults
import edu.austral.dissis.chess.engine.movement.Movement

interface PieceRuleValidator {
  fun checkMovement(move: Movement): ValidationResults
}