package edu.austral.dissis.twoDBoardGame.rules.uniqueRules

import edu.austral.dissis.twoDBoardGame.board.DefaultBoard
import edu.austral.dissis.twoDBoardGame.game.Movement
import edu.austral.dissis.twoDBoardGame.results.Invalid
import edu.austral.dissis.twoDBoardGame.results.RuleResult
import edu.austral.dissis.twoDBoardGame.results.Valid
import edu.austral.dissis.twoDBoardGame.rules.RuleManager

class Enemy : RuleManager {
  override fun checkMovement(
    board: DefaultBoard,
    movement: Movement
  ): RuleResult {

    board.getPiece(movement.getTo())?.let {

      if (it.getColor() != movement.getTurn()) return Valid()
      else Invalid("Invalid Movement: Pawn can't eat its own piece")

    }

    return Invalid("Invalid Movement: Pawn can't move to an empty space")
  }
}