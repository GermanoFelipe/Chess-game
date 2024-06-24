package edu.austral.dissis.twoDBoardGame.rules.directionsValidator

import edu.austral.dissis.twoDBoardGame.game.Game
import edu.austral.dissis.twoDBoardGame.game.Movement
import edu.austral.dissis.twoDBoardGame.results.Invalid
import edu.austral.dissis.twoDBoardGame.results.RuleResult
import edu.austral.dissis.twoDBoardGame.results.Valid
import edu.austral.dissis.twoDBoardGame.rules.RuleManager

class ToLeftValidator: RuleManager {
  override fun checkMovement(game: Game, movement: Movement): RuleResult {
    return if (movement.getFrom().column > movement.getTo().column){
      Valid()
    } else Invalid("Invalid movement to the left")
  }
}