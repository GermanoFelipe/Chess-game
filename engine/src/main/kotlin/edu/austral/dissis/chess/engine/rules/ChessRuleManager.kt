package edu.austral.dissis.chess.engine.rules

import edu.austral.dissis.chess.engine.game.Game
import edu.austral.dissis.chess.engine.game.results.InvalidMovement
import edu.austral.dissis.chess.engine.game.results.MovementResult
import edu.austral.dissis.chess.engine.game.results.Valid
import edu.austral.dissis.chess.engine.movement.Movement

class ChessRuleManager: RuleManager {
  override fun checkMovement(game: Game, movement: Movement): MovementResult {
    val pieceToCheck = movement.getPiece()
    val colorToCheck = pieceToCheck.pieceColor
    val ruleResult = pieceToCheck.pieceRules
    val from = movement.getFrom()
    val to = movement.getTo()
    val board = movement.getBoard()

    val state = ruleResult.checkMovement(from, to, board)
    return if (state){
      Valid(game)
    } else InvalidMovement()
  }
}