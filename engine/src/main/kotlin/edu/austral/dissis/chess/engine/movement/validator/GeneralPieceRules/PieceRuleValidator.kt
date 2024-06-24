package edu.austral.dissis.chess.engine.movement.validator.GeneralPieceRules

import edu.austral.dissis.twoDBoardGame.results.MovementResult
import edu.austral.dissis.twoDBoardGame.game.Movement

interface PieceRuleValidator {
  fun checkMovement(move: Movement): MovementResult
}