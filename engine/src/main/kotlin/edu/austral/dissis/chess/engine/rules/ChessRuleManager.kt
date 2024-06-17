package edu.austral.dissis.chess.engine.rules

import edu.austral.dissis.chess.engine.board.Board
import edu.austral.dissis.chess.engine.game.results.MovementResult
import edu.austral.dissis.chess.engine.piece.Position

class ChessRuleManager: RuleManager {
  override fun checkMovement(from: Position, to: Position, defaultBoard: Board): MovementResult {
    TODO("Not yet implemented")
  }
}