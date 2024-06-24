package edu.austral.dissis.chess.engine.rules

import edu.austral.dissis.twoDBoardGame.game.Game
import edu.austral.dissis.twoDBoardGame.game.Movement
import edu.austral.dissis.twoDBoardGame.results.Invalid
import edu.austral.dissis.twoDBoardGame.results.RuleResult
import edu.austral.dissis.twoDBoardGame.results.Valid
import edu.austral.dissis.twoDBoardGame.rules.RuleManager
import kotlin.math.abs

class KnightMovement: RuleManager {
  override fun checkMovement(game: Game, movement: Movement): RuleResult {
    val oldColumn = movement.getFrom().column
    val oldRow = movement.getFrom().row
    val newColumn = movement.getTo().column
    val newRow = movement.getTo().row

    val absColumn = abs(oldColumn - newColumn)
    val absRow = abs(oldRow - newRow)

    return if ((absColumn == 2 && absRow == 1) || (absColumn == 1 && absRow == 2)) {
      Valid()
    } else Invalid("Invalid Movement: Knight can only move in L shape")
  }
}