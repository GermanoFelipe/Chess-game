package edu.austral.dissis.chess.engine.movement.validator.limits

import edu.austral.dissis.twoDBoardGame.game.Game
import edu.austral.dissis.twoDBoardGame.game.Movement
import edu.austral.dissis.twoDBoardGame.results.Invalid
import edu.austral.dissis.twoDBoardGame.results.RuleResult
import edu.austral.dissis.twoDBoardGame.results.Valid
import edu.austral.dissis.twoDBoardGame.rules.RuleManager

class HasMovedLimit: RuleManager {
  override fun checkMovement(game: Game, movement: Movement): RuleResult {
    val piece = game.board.getPiece(movement.getFrom())
    val pieceMoved = piece!!.hasMoved() //true if moved
    return if (pieceMoved) Invalid("Piece has already moved")
    else Valid()
  }
}