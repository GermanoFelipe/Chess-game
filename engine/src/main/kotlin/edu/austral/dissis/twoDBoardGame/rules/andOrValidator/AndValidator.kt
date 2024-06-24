package edu.austral.dissis.twoDBoardGame.rules.andOrValidator

import edu.austral.dissis.chess.engine.board.DefaultBoard
import edu.austral.dissis.chess.engine.movement.validator.GeneralPieceRules.PieceRuleValidator
import edu.austral.dissis.twoDBoardGame.game.Game
import edu.austral.dissis.twoDBoardGame.game.Movement
import edu.austral.dissis.twoDBoardGame.position.Position
import edu.austral.dissis.twoDBoardGame.results.Invalid
import edu.austral.dissis.twoDBoardGame.results.RuleResult
import edu.austral.dissis.twoDBoardGame.results.Valid
import edu.austral.dissis.twoDBoardGame.rules.RuleManager
import java.lang.IllegalArgumentException

class AndValidator (
                    val rules: List<RuleManager>
                    ): RuleManager {
  override fun checkMovement(game: Game, movement: Movement): RuleResult {
    var validResult: RuleResult= Valid()
    for (rule in rules) {
      val result = rule.checkMovement(game, movement)
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
            return Valid()
          }
          is Invalid -> {
            throw IllegalArgumentException("Invalid result")
          }
        }
      }
      is Invalid -> {
        if (newResult is Valid) {
          return newResult
        } else {
          return Invalid()
        }
      }
    }
  }
}