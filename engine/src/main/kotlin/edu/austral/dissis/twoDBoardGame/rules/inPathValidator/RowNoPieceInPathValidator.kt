package edu.austral.dissis.twoDBoardGame.rules.inPathValidator

import edu.austral.dissis.twoDBoardGame.game.Game
import edu.austral.dissis.twoDBoardGame.game.Movement
import edu.austral.dissis.twoDBoardGame.position.Position
import edu.austral.dissis.twoDBoardGame.results.Invalid
import edu.austral.dissis.twoDBoardGame.results.RuleResult
import edu.austral.dissis.twoDBoardGame.results.Valid
import edu.austral.dissis.twoDBoardGame.rules.RuleManager

class RowNoPieceInPathValidator : RuleManager {
  override fun checkMovement(game: Game, movement: Movement): RuleResult {
    val min = minOf(movement.getFrom().column, movement.getTo().column)
    val max = maxOf(movement.getFrom().column, movement.getTo().column)

  for (i in min + 1 until max) {
      return if (movement.getBoard().getPiece(Position(movement.getFrom().row, i)) != null) {
        Invalid()
      } else Valid()
    }
    return Valid()
  }
}