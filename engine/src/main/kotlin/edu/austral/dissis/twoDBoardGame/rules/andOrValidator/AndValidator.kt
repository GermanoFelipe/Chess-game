package edu.austral.dissis.twoDBoardGame.rules.andOrValidator

import edu.austral.dissis.twoDBoardGame.board.DefaultBoard
import edu.austral.dissis.twoDBoardGame.game.Game
import edu.austral.dissis.twoDBoardGame.game.Movement
import edu.austral.dissis.twoDBoardGame.results.ActionResult
import edu.austral.dissis.twoDBoardGame.results.Invalid
import edu.austral.dissis.twoDBoardGame.results.RuleResult
import edu.austral.dissis.twoDBoardGame.results.Valid
import edu.austral.dissis.twoDBoardGame.rules.RuleManager
import java.lang.IllegalArgumentException

class AndValidator (
                    val rules: List<RuleManager>,
                    val actions: List<ActionResult> = emptyList()
                    ): RuleManager {
  override fun checkMovement(board: DefaultBoard, movement: Movement): RuleResult {
    var validResult: RuleResult= Valid(actions)
    for (rule in rules) {
      val result = rule.checkMovement(board, movement)
      if (result is Invalid) {
        return result
      }
      validResult = updateResult(validResult, result)
    }
    return validResult
  }

  private fun updateResult(result: RuleResult, newResult: RuleResult): RuleResult {
    return when (result){
      is Valid -> {
        when(newResult){
          is Valid -> {
            return Valid(result.getActionResult() + newResult.getActionResult())
          }
          is Invalid -> {
            throw IllegalArgumentException("Should not be Invalid")
          }
        }
      }
      is Invalid -> {
        throw IllegalArgumentException("Should not be Invalid")
      }
    }
  }

  fun withSpecial(action: List<ActionResult>): RuleManager{
    return AndValidator(rules, action)
  }
}