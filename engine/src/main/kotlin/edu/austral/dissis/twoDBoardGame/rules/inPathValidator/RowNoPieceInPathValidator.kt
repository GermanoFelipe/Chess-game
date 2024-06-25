package edu.austral.dissis.twoDBoardGame.rules.inPathValidator

import edu.austral.dissis.twoDBoardGame.board.DefaultBoard
import edu.austral.dissis.twoDBoardGame.game.Game
import edu.austral.dissis.twoDBoardGame.game.Movement
import edu.austral.dissis.twoDBoardGame.position.Position
import edu.austral.dissis.twoDBoardGame.results.Invalid
import edu.austral.dissis.twoDBoardGame.results.RuleResult
import edu.austral.dissis.twoDBoardGame.results.Valid
import edu.austral.dissis.twoDBoardGame.rules.RuleManager

class RowNoPieceInPathValidator : RuleManager {
  override fun checkMovement(board: DefaultBoard, movement: Movement): RuleResult {
    if (movement.getFrom().row != movement.getTo().row) {
      return Valid()
    }

    var currentPosition = if (movement.getFrom().column < movement.getTo().column) {
      Position(movement.getFrom().row, movement.getFrom().column + 1)
    } else
      Position(movement.getFrom().row, movement.getFrom().column - 1)

    while (currentPosition.column != movement.getTo().column) {
      if (board.getPiece(currentPosition) != null) {
        return Invalid("There is a piece in the path")
      }

      currentPosition = if (currentPosition.column < movement.getTo().column) {
        Position(currentPosition.row, currentPosition.column + 1)
      } else {
        Position(currentPosition.row, currentPosition.column - 1)
      }
    }
    return Valid()
  }
}