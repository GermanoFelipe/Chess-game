package edu.austral.dissis.chess.engine.game

import edu.austral.dissis.chess.engine.board.Board
import edu.austral.dissis.chess.engine.movement.validator.MovementValidator
import edu.austral.dissis.chess.engine.rules.uniques.UniquesRules
import edu.austral.dissis.chess.engine.rules.winCondition.WinCondition
import edu.austral.dissis.chess.engine.game.Results
import edu.austral.dissis.chess.engine.piece.Position

class DefaultResult(val board: Board, val winConditions: List<WinCondition>): GameResult {

  override fun possibleOutcome(board: Board, winConditions: List<WinCondition>): Results {
    for (winCondition in winConditions) {
      if (winCondition.checkRule()) {
        return Results.WIN
      }
    }
    return Results.CONTINUE
  }

}