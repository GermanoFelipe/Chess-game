package edu.austral.dissis.chess.engine.rules.winCondition

import edu.austral.dissis.twoDBoardGame.board.DefaultBoard
import edu.austral.dissis.twoDBoardGame.game.Movement
import edu.austral.dissis.twoDBoardGame.results.Invalid
import edu.austral.dissis.twoDBoardGame.results.RuleResult
import edu.austral.dissis.twoDBoardGame.results.Valid
import edu.austral.dissis.twoDBoardGame.rules.RuleManager

class IsNotInCheckValidator : RuleManager {
  val check = Check()

  override fun checkMovement(board: DefaultBoard, movement: Movement): RuleResult {
    val boardMoved = board.movePiece(movement.getFrom(), movement.getTo())

    if (check.inCheck(boardMoved, movement.getTurn() )){
      return Invalid("You cant leave in Check")
    }
    return Valid()
  }
}