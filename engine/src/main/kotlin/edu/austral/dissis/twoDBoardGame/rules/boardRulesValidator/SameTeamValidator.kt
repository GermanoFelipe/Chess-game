package edu.austral.dissis.twoDBoardGame.rules.boardRulesValidator

import edu.austral.dissis.twoDBoardGame.game.Game
import edu.austral.dissis.twoDBoardGame.game.Movement
import edu.austral.dissis.twoDBoardGame.results.Invalid
import edu.austral.dissis.twoDBoardGame.results.RuleResult
import edu.austral.dissis.twoDBoardGame.results.Valid
import edu.austral.dissis.twoDBoardGame.rules.RuleManager

class SameTeamValidator: RuleManager {
  override fun checkMovement(game: Game, movement: Movement): RuleResult {
  val fromPiece = movement.getBoard().getPiece(movement.getFrom()) ?: return Invalid("No piece to selected")

    movement.getBoard().getPiece(movement.getTo())?.let {
        if (it.pieceColor == fromPiece.pieceColor) return Invalid("You can't move to a square with a piece of the same team")
        else return Valid()
    }
    return Valid()
  }
}