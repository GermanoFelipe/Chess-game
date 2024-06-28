package edu.austral.dissis.twoDBoardGame.rules.inPathValidator

import edu.austral.dissis.twoDBoardGame.board.DefaultBoard
import edu.austral.dissis.twoDBoardGame.game.Movement
import edu.austral.dissis.twoDBoardGame.position.Position
import edu.austral.dissis.twoDBoardGame.results.Invalid
import edu.austral.dissis.twoDBoardGame.results.RuleResult
import edu.austral.dissis.twoDBoardGame.results.Valid
import edu.austral.dissis.twoDBoardGame.rules.RuleManager
import kotlin.math.abs

class DiagonalNoPieceInPathValidator : RuleManager {
  override fun checkMovement(
    board: DefaultBoard,
    movement: Movement
  ): RuleResult {

    if (abs(movement.getFrom().row - movement.getTo().row) !=
            abs(movement.getFrom().column - movement.getTo().column)) {
      return Valid()
    }

    var currentPosition = movement.getFrom()

    while (currentPosition.row != movement.getTo().row && currentPosition.column != movement.getTo().column) {
      currentPosition = if (currentPosition.row < movement.getTo().row && currentPosition.column < movement.getTo().column) {
        Position(currentPosition.row + 1, currentPosition.column + 1)
      } else if (currentPosition.row < movement.getTo().row) {
        Position(currentPosition.row + 1, currentPosition.column - 1)
      } else if (currentPosition.column < movement.getTo().column) {
        Position(currentPosition.row - 1, currentPosition.column + 1)
      } else {
        Position(currentPosition.row - 1, currentPosition.column - 1)
      }

      if (currentPosition.row != movement.getTo().row &&
              currentPosition.column != movement.getTo().column && board.getPiece(currentPosition) != null) {
        return Invalid("There is a piece in the path")
    }
    }
    return Valid()
  }
}