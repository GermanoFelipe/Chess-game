package edu.austral.dissis.chess.engine.movement.validator.inPath

import edu.austral.dissis.twoDBoardGame.game.Game
import edu.austral.dissis.twoDBoardGame.game.Movement
import edu.austral.dissis.twoDBoardGame.results.RuleResult
import edu.austral.dissis.twoDBoardGame.results.Valid
import edu.austral.dissis.twoDBoardGame.rules.RuleManager

class CanJumpValidator : RuleManager {
  override fun checkMovement(game: Game, movement: Movement): RuleResult {
    return Valid()
  }
}