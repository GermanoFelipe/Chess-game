package edu.austral.dissis.chess.engine.rules

import edu.austral.dissis.chess.engine.board.Board
import edu.austral.dissis.chess.engine.game.results.InvalidNoPiece
import edu.austral.dissis.chess.engine.game.results.MovementResult
import edu.austral.dissis.chess.engine.movement.Movement
import edu.austral.dissis.chess.engine.piece.Position

class ChessRuleManager: RuleManager {
  override fun checkMovement(movement: Movement): MovementResult {
    val pieceToCheck = movement.getPiece()
    pieceToCheck.type
    return InvalidNoPiece()
  }
}