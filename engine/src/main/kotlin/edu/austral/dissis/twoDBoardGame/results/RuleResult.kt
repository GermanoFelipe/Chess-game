package edu.austral.dissis.twoDBoardGame.results
sealed interface RuleResult

class Valid (
  private val actions: List<ActionResult> = emptyList()
): RuleResult {

  fun getActionResult(): List<ActionResult> {
    return actions
  }
}
class Invalid (
  var message: String
  ): RuleResult