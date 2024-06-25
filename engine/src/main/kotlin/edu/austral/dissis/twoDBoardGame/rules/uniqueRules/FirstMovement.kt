package edu.austral.dissis.twoDBoardGame.rules.uniqueRules

import edu.austral.dissis.twoDBoardGame.board.DefaultBoard
import edu.austral.dissis.twoDBoardGame.game.Movement
import edu.austral.dissis.twoDBoardGame.results.Invalid
import edu.austral.dissis.twoDBoardGame.results.RuleResult
import edu.austral.dissis.twoDBoardGame.results.Valid
import edu.austral.dissis.twoDBoardGame.rules.RuleManager

class FirstMovement: RuleManager {
  override fun checkMovement(board: DefaultBoard, movement: Movement): RuleResult {
    val piece = board.getPiece(movement.getFrom()) ?: return Invalid("No piece in position")

    if (piece.hasMoved) return Invalid("Piece has already moved")
    return Valid()
  }
}