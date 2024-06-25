package edu.austral.dissis.twoDBoardGame.rules.inPathValidator

import edu.austral.dissis.twoDBoardGame.board.DefaultBoard
import edu.austral.dissis.twoDBoardGame.game.Movement
import edu.austral.dissis.twoDBoardGame.position.Position
import edu.austral.dissis.twoDBoardGame.results.Invalid
import edu.austral.dissis.twoDBoardGame.results.RuleResult
import edu.austral.dissis.twoDBoardGame.results.Valid
import edu.austral.dissis.twoDBoardGame.rules.RuleManager

class ColumnNoPieceInPathValidator (private val inclusive: Boolean = false): RuleManager {
  override fun checkMovement(board: DefaultBoard, movement: Movement): RuleResult {

    var currentPosition = if(movement.getFrom().row < movement.getTo().row){
      Position(movement.getFrom().row + 1, movement.getFrom().column)
    } else
      Position(movement.getFrom().row - 1, movement.getFrom().column)

    while (currentPosition.row != movement.getTo().row) {
      if (board.getPiece(currentPosition) != null) {
        return Invalid("There is a piece in the path")
      }
      currentPosition = if (currentPosition.row < movement.getTo().row) {
        Position(currentPosition.row + 1, currentPosition.column)
      } else {
        Position(currentPosition.row - 1, currentPosition.column)
      }
    }
    return if(!(inclusive && board.getPiece(movement.getTo()) != null)){
      Valid()
    } else {
      Invalid("There is a piece in the destination")
    }
  }
}
