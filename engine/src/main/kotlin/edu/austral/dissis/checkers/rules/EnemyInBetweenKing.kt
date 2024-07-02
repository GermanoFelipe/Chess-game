package edu.austral.dissis.checkers.rules

import edu.austral.dissis.twoDBoardGame.board.DefaultBoard
import edu.austral.dissis.twoDBoardGame.game.Movement
import edu.austral.dissis.twoDBoardGame.piece.Color
import edu.austral.dissis.twoDBoardGame.piece.Piece
import edu.austral.dissis.twoDBoardGame.position.Position
import edu.austral.dissis.twoDBoardGame.results.*
import edu.austral.dissis.twoDBoardGame.rules.RuleManager

class EnemyInBetweenKing: RuleManager{
  override fun checkMovement(board: DefaultBoard, movement: Movement): RuleResult {
    val midPos = getMidPosition(movement)
    val midPiece = board.getPiece(midPos)

    return if (isPresentAndOppositeColor(midPiece, movement.getTurn()))
      Valid(getActions(movement.getFrom(), midPos, movement.getTurn()))
    else
      Invalid("There is no enemy in between")
  }

  private fun getMidPosition(movement: Movement): Position {
    val midRow = (movement.getFrom().row + movement.getTo().row) / 2
    val midColumn = (movement.getFrom().column + movement.getTo().column) / 2
    return Position(midRow, midColumn)
  }

  private fun isPresentAndOppositeColor(piece: Piece?, color: Color): Boolean {
    return if (piece != null){
      piece.getColor() != color
    } else {
      false
    }
  }

  private fun getActions(from: Position, position: Position, color: Color): List<ActionResult>{
    val relativeFrom =
      if (color == Color.WHITE) {
        RelativePosition(position.row - from.row, position.column - from.column)
      } else {
        if (from.row < position.row)
          RelativePosition(from.row - position.row, position.column - from.column)
        else
          RelativePosition(position.row - from.row, position.column - from.column)
      }
    return listOf(
      RemoveAction(relativeFrom),
    )
  }
}