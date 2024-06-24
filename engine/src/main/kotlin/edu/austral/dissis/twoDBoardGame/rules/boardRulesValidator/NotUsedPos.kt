package edu.austral.dissis.twoDBoardGame.rules.boardRulesValidator

import edu.austral.dissis.twoDBoardGame.game.Game
import edu.austral.dissis.twoDBoardGame.game.Movement
import edu.austral.dissis.twoDBoardGame.results.Invalid
import edu.austral.dissis.twoDBoardGame.results.RuleResult
import edu.austral.dissis.twoDBoardGame.results.Valid
import edu.austral.dissis.twoDBoardGame.rules.RuleManager

class NotUsedPos: RuleManager {
  override fun checkMovement(game: Game, movement: Movement): RuleResult {
    return if (movement.getBoard().getPiece(movement.getTo()) == null) Valid() else Invalid("Position in use")
  }
}