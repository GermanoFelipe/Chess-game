package edu.austral.dissis.twoDBoardGame.rules.directions

import edu.austral.dissis.chess.engine.piece.Color
import edu.austral.dissis.twoDBoardGame.game.Game
import edu.austral.dissis.twoDBoardGame.game.Movement
import edu.austral.dissis.twoDBoardGame.position.Position
import edu.austral.dissis.twoDBoardGame.results.Invalid
import edu.austral.dissis.twoDBoardGame.results.RuleResult
import edu.austral.dissis.twoDBoardGame.results.Valid
import edu.austral.dissis.twoDBoardGame.rules.RuleManager

class VerticalBack: RuleManager {
  override fun checkMovement(game: Game, movement: Movement): RuleResult {
    return if (movement.getPiece().pieceColor == Color.WHITE) {
      forWhite(movement.getFrom(), movement.getTo())
    } else {
      forBlack(movement.getFrom(), movement.getTo())
    }
  }

  fun forWhite(from: Position, to: Position): RuleResult {
    return if (from.row > to.row) Valid() else Invalid()
  }

  fun forBlack(from: Position, to: Position): RuleResult {
    return if (from.row < to.row) Valid() else Invalid()
  }
}