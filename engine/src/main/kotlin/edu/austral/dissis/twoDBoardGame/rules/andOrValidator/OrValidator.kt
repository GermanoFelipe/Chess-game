package edu.austral.dissis.twoDBoardGame.rules.andOrValidator

import edu.austral.dissis.twoDBoardGame.game.Game
import edu.austral.dissis.twoDBoardGame.game.Movement
import edu.austral.dissis.twoDBoardGame.results.Invalid
import edu.austral.dissis.twoDBoardGame.results.RuleResult
import edu.austral.dissis.twoDBoardGame.results.Valid
import edu.austral.dissis.twoDBoardGame.rules.RuleManager

class OrValidator(
  val rules: List<RuleManager>
  )
  : RuleManager {

  override fun checkMovement(game: Game, movement: Movement): RuleResult {
    for (rule in rules) {
      return when (val result = rule.checkMovement(game, movement)) {
        is Valid -> {
          return Valid()
        }
        is Invalid -> {
          continue
        }
      }
    }
    return Invalid("Invalid Movement")
  }
}