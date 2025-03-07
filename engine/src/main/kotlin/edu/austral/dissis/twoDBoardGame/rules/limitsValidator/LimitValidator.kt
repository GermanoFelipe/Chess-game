package edu.austral.dissis.twoDBoardGame.rules.limitsValidator

import edu.austral.dissis.twoDBoardGame.board.DefaultBoard
import edu.austral.dissis.twoDBoardGame.game.Movement
import edu.austral.dissis.twoDBoardGame.results.Invalid
import edu.austral.dissis.twoDBoardGame.results.RuleResult
import edu.austral.dissis.twoDBoardGame.results.Valid
import edu.austral.dissis.twoDBoardGame.rules.RuleManager

class LimitValidator(
  private val limit: Int
                    ): RuleManager {
  override fun checkMovement(
    board: DefaultBoard,
    movement: Movement
  ): RuleResult {

    val absColumn = kotlin.math.abs(movement.getFrom().column - movement.getTo().column)
    val absRow = kotlin.math.abs(movement.getFrom().row - movement.getTo().row)

    return if (absColumn <= limit && absRow <= limit) {
      Valid()

    } else Invalid("Invalid Movement: Limit Exceeded")
  }
}