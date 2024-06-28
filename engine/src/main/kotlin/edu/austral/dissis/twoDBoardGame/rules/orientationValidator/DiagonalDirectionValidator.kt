package edu.austral.dissis.twoDBoardGame.rules.orientationValidator

import edu.austral.dissis.twoDBoardGame.board.DefaultBoard
import edu.austral.dissis.twoDBoardGame.game.Movement
import edu.austral.dissis.twoDBoardGame.results.Invalid
import edu.austral.dissis.twoDBoardGame.results.RuleResult
import edu.austral.dissis.twoDBoardGame.results.Valid
import edu.austral.dissis.twoDBoardGame.rules.RuleManager
import kotlin.math.abs

class DiagonalDirectionValidator: RuleManager {
  override fun checkMovement(
    board: DefaultBoard,
    movement: Movement
  ): RuleResult {

    return if (abs(movement.getFrom().row - movement.getTo().row) ==
      abs(movement.getFrom().column - movement.getTo().column)) {

      Valid()

    } else {
      Invalid("Invalid Movement: Diagonal movement must have the same distance in rows and columns")
    }
  }
}