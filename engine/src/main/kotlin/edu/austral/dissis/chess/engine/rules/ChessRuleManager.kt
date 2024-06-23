package edu.austral.dissis.chess.engine.rules

import edu.austral.dissis.chess.engine.board.Board
import edu.austral.dissis.chess.engine.game.Game
import edu.austral.dissis.chess.engine.game.results.InvalidMovement
import edu.austral.dissis.chess.engine.game.results.MovementResult
import edu.austral.dissis.chess.engine.game.results.ValidMovement
import edu.austral.dissis.chess.engine.movement.Movement
import edu.austral.dissis.chess.engine.piece.Position

class ChessRuleManager: RuleManager {
  override fun checkMovement(game: Game, movement: Movement): MovementResult {
    val pieceToCheck = movement.getPiece()
    val ruleResult = pieceToCheck.pieceRules
    val from = movement.getFrom()
    val to = movement.getTo()
    val board = movement.getBoard()

    val state = ruleResult.checkMovement(from, to, board)
    return if (state){
      ValidMovement(game)
    } else InvalidMovement()
  }
}