package edu.austral.dissis.twoDBoardGame.rules.boardRulesValidator

import edu.austral.dissis.twoDBoardGame.game.Game
import edu.austral.dissis.twoDBoardGame.game.Movement
import edu.austral.dissis.twoDBoardGame.results.Invalid
import edu.austral.dissis.twoDBoardGame.results.RuleResult
import edu.austral.dissis.twoDBoardGame.results.Valid
import edu.austral.dissis.twoDBoardGame.rules.RuleManager

class TurnValidator: RuleManager {
  override fun checkMovement(game: Game, movement: Movement): RuleResult {
    val fromPiece = movement.getBoard().getPiece(movement.getFrom())

    return if (fromPiece != null) {
      if (fromPiece.pieceColor == game.turn.actualTurn()) Valid() else Invalid()
    } else Invalid()
  }
}