package edu.austral.dissis.twoDBoardGame.rules.orientationValidator

import edu.austral.dissis.twoDBoardGame.game.Game
import edu.austral.dissis.twoDBoardGame.game.Movement
import edu.austral.dissis.twoDBoardGame.results.Invalid
import edu.austral.dissis.twoDBoardGame.results.RuleResult
import edu.austral.dissis.twoDBoardGame.results.Valid
import edu.austral.dissis.twoDBoardGame.rules.RuleManager
import kotlin.math.absoluteValue

class DiagonalDirectionValidator: RuleManager {
  override fun checkMovement(game: Game, movement: Movement): RuleResult {
    if (movement.getFrom().row != movement.getTo().row && movement.getFrom().column != movement.getTo().column &&
      (movement.getFrom().row - movement.getTo().row).absoluteValue == (movement.getFrom().column - movement.getTo().column).absoluteValue)
      return Valid()
    else return Invalid("Invalid Movement: Diagonal Direction is not allowed")
  }
}