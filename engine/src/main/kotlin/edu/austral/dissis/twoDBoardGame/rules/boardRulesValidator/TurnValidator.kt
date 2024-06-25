package edu.austral.dissis.twoDBoardGame.rules.boardRulesValidator

import edu.austral.dissis.twoDBoardGame.board.DefaultBoard
import edu.austral.dissis.twoDBoardGame.game.Movement
import edu.austral.dissis.twoDBoardGame.results.Invalid
import edu.austral.dissis.twoDBoardGame.results.RuleResult
import edu.austral.dissis.twoDBoardGame.results.Valid
import edu.austral.dissis.twoDBoardGame.rules.RuleManager

class TurnValidator: RuleManager {
  override fun checkMovement(board: DefaultBoard, movement: Movement): RuleResult {
    val fromPiece = board.getPiece(movement.getFrom()) ?: return Invalid("No piece to selected")

    return if (fromPiece.pieceColor == movement.getTurn()) Valid()
    else Invalid("Not your turn")
  }
}