package edu.austral.dissis.twoDBoardGame.rules.andOrValidator

import edu.austral.dissis.twoDBoardGame.board.DefaultBoard
import edu.austral.dissis.twoDBoardGame.game.Movement
import edu.austral.dissis.twoDBoardGame.results.ActionResult
import edu.austral.dissis.twoDBoardGame.results.Invalid
import edu.austral.dissis.twoDBoardGame.results.RuleResult
import edu.austral.dissis.twoDBoardGame.results.Valid
import edu.austral.dissis.twoDBoardGame.rules.RuleManager

class OrValidator(
  private val rules: List<RuleManager>,
  private val actions: List<ActionResult> = emptyList()
  )
  : RuleManager {

  override fun checkMovement(
    board: DefaultBoard,
    movement: Movement
  ): RuleResult {

    for (rule in rules) {
      return when (val result = rule.checkMovement(board, movement)) {
        is Valid ->  return Valid(result.getActionResult() + actions)
        is Invalid -> continue
      }
    }
    return Invalid("Invalid Movement")
  }

  fun withSpecial(actions: List<ActionResult>): RuleManager {
    return OrValidator(rules, actions)
  }
}