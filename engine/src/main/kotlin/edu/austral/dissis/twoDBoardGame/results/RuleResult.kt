package edu.austral.dissis.twoDBoardGame.results

import edu.austral.dissis.twoDBoardGame.game.Game

sealed interface RuleResult {
  fun getMessage(): String
}

class Valid : RuleResult {
  override fun getMessage(): String {
    return "Valid Movement"
  }
}
class Invalid (var message: String): RuleResult {
  override fun getMessage(): String {
    return message
  }
}