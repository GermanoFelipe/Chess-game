package edu.austral.dissis.twoDBoardGame.rules.inPathValidator

import edu.austral.dissis.twoDBoardGame.game.Game
import edu.austral.dissis.twoDBoardGame.game.Movement
import edu.austral.dissis.twoDBoardGame.position.Position
import edu.austral.dissis.twoDBoardGame.results.Invalid
import edu.austral.dissis.twoDBoardGame.results.RuleResult
import edu.austral.dissis.twoDBoardGame.results.Valid
import edu.austral.dissis.twoDBoardGame.rules.RuleManager
import kotlin.math.abs

class DiagonalNoPieceInPathValidator : RuleManager {
  override fun checkMovement(game: Game, movement: Movement): RuleResult {
    val rowDiff = movement.getTo().row - movement.getFrom().row
    val steps = abs(rowDiff)
    val minRow = minOf(movement.getFrom().row, movement.getTo().row)
    val minColumn = minOf(movement.getFrom().column, movement.getTo().column)
    for (i in 1 until steps) {
      if (movement.getBoard().getPiece(Position(minRow + i, minColumn + i)) != null) return Invalid()
    }
    return Valid()
  }
}