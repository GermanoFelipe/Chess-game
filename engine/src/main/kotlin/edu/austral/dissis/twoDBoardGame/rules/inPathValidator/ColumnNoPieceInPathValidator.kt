package edu.austral.dissis.twoDBoardGame.rules.inPathValidator

import edu.austral.dissis.twoDBoardGame.piece.Color
import edu.austral.dissis.twoDBoardGame.game.Game
import edu.austral.dissis.twoDBoardGame.game.Movement
import edu.austral.dissis.twoDBoardGame.position.Position
import edu.austral.dissis.twoDBoardGame.results.Invalid
import edu.austral.dissis.twoDBoardGame.results.RuleResult
import edu.austral.dissis.twoDBoardGame.results.Valid
import edu.austral.dissis.twoDBoardGame.rules.RuleManager

class ColumnNoPieceInPathValidator : RuleManager {
  override fun checkMovement(game: Game, movement: Movement): RuleResult {
    val min = minOf(movement.getFrom().row, movement.getTo().row)
    val max = maxOf(movement.getFrom().row, movement.getTo().row)
    val pieceToUse = movement.getBoard().getPiece(movement.getFrom()) ?: return Invalid("There is no piece to move")

    if (pieceToUse.pieceColor == Color.WHITE) {
      for (i in max + 1 until min) {
        return if (movement.getBoard().getPiece(Position(i, movement.getFrom().column)) != null) {
          Invalid("There is a piece in the path")
        } else Valid()
      }
    } else {
      for (i in min + 1 until max) {
        return if (movement.getBoard().getPiece(Position(i, movement.getFrom().column)) != null) {
          Invalid("There is a piece in the path")
        } else Valid()
      }
    }
    return Valid()
  }
}
