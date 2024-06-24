package edu.austral.dissis.twoDBoardGame.rules.andOrValidator

import edu.austral.dissis.chess.engine.board.DefaultBoard
import edu.austral.dissis.chess.engine.movement.validator.GeneralPieceRules.PieceRuleValidator
import edu.austral.dissis.twoDBoardGame.game.Game
import edu.austral.dissis.twoDBoardGame.game.Movement
import edu.austral.dissis.twoDBoardGame.position.Position
import edu.austral.dissis.twoDBoardGame.results.Invalid
import edu.austral.dissis.twoDBoardGame.results.MovementResult
import edu.austral.dissis.twoDBoardGame.results.RuleResult
import edu.austral.dissis.twoDBoardGame.results.Valid
import edu.austral.dissis.twoDBoardGame.rules.RuleManager
import java.lang.IllegalArgumentException

class OrValidator(
  val rules: List<RuleManager>,
  val validator1: PieceRuleValidator,
  val validator2: PieceRuleValidator
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
    return Invalid()
  }
}