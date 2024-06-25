package edu.austral.dissis.twoDBoardGame.rules.directionsValidator

import edu.austral.dissis.twoDBoardGame.board.DefaultBoard
import edu.austral.dissis.twoDBoardGame.piece.Color
import edu.austral.dissis.twoDBoardGame.game.Movement
import edu.austral.dissis.twoDBoardGame.position.Position
import edu.austral.dissis.twoDBoardGame.results.Invalid
import edu.austral.dissis.twoDBoardGame.results.RuleResult
import edu.austral.dissis.twoDBoardGame.results.Valid
import edu.austral.dissis.twoDBoardGame.rules.RuleManager

class VerticalBack: RuleManager {
  override fun checkMovement(board: DefaultBoard, movement: Movement): RuleResult {
    return if (movement.getTurn() == Color.WHITE) forWhite(movement.getFrom(), movement.getTo())
    else forBlack(movement.getFrom(), movement.getTo())
  }

  fun forWhite(from: Position, to: Position): RuleResult {
    return if (from.row > to.row) Valid()
    else Invalid("Invalid movement, you can only move backwards.")
  }

  fun forBlack(from: Position, to: Position): RuleResult {
    return if (from.row < to.row)
      Valid() else Invalid("Invalid movement, you can only move backwards.")
  }
}