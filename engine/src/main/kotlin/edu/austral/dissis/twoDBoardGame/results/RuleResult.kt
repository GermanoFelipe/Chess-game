package edu.austral.dissis.twoDBoardGame.results

import edu.austral.dissis.twoDBoardGame.game.Game

sealed interface RuleResult {
}

class Valid : RuleResult {
  fun getMessage(): String {
    return "Valid Movement"
  }
}
class Invalid (var message: String): RuleResult {
}