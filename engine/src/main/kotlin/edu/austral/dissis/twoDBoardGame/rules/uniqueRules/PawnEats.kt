package edu.austral.dissis.twoDBoardGame.rules.uniqueRules

import edu.austral.dissis.twoDBoardGame.game.Game
import edu.austral.dissis.twoDBoardGame.game.Movement
import edu.austral.dissis.twoDBoardGame.results.Invalid
import edu.austral.dissis.twoDBoardGame.results.RuleResult
import edu.austral.dissis.twoDBoardGame.results.Valid
import edu.austral.dissis.twoDBoardGame.rules.RuleManager

class PawnEats : RuleManager {
  override fun checkMovement(game: Game, movement: Movement): RuleResult {
    val pieceToEat = game.getBoard().getPiece(movement.getTo()) ?: return Invalid("No piece to eat")
    val piece = game.getBoard().getPiece(movement.getFrom()) ?: return Invalid("No piece to move")
    if (pieceToEat.pieceColor != piece.pieceColor) return Valid()
    else return Invalid("Can't eat your own piece")
  }
}