package edu.austral.dissis.twoDBoardGame.rules.boardRulesValidator

import edu.austral.dissis.twoDBoardGame.board.DefaultBoard
import edu.austral.dissis.twoDBoardGame.game.Movement
import edu.austral.dissis.twoDBoardGame.results.Invalid
import edu.austral.dissis.twoDBoardGame.results.RuleResult
import edu.austral.dissis.twoDBoardGame.results.Valid
import edu.austral.dissis.twoDBoardGame.rules.RuleManager

class NotUsedPos: RuleManager {
  override fun checkMovement(board: DefaultBoard, movement: Movement): RuleResult {
    return if (board.getPiece(movement.getTo()) == null) Valid()
    else Invalid("Position in use")
  }
}