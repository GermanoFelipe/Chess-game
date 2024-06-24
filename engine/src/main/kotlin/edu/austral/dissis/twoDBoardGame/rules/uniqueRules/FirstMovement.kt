package edu.austral.dissis.twoDBoardGame.rules.uniqueRules

import edu.austral.dissis.twoDBoardGame.game.Game
import edu.austral.dissis.twoDBoardGame.game.Movement
import edu.austral.dissis.twoDBoardGame.results.Invalid
import edu.austral.dissis.twoDBoardGame.results.RuleResult
import edu.austral.dissis.twoDBoardGame.results.Valid
import edu.austral.dissis.twoDBoardGame.rules.RuleManager

class FirstMovement: RuleManager {
  override fun checkMovement(game: Game, movement: Movement): RuleResult {
    val pieceToCheck = game.board.getPiece(movement.getFrom()) ?: return Invalid("No piece to move")

    return if (pieceToCheck.hasMoved) Invalid("Piece has already moved")
    else Valid()
  }
}