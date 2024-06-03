package edu.austral.dissis.chess.engine.game

import edu.austral.dissis.chess.engine.board.DefaultBoard
import edu.austral.dissis.chess.engine.rules.winCondition.WinCondition

class DefaultResult(val defaultBoard: DefaultBoard, val winConditions: List<WinCondition>): GameResult {

  override fun possibleOutcome(defaultBoard: DefaultBoard, winConditions: List<WinCondition>): Results {
    for (winCondition in winConditions) {
      if (winCondition.checkRule()) {
        return Results.WIN
      }
    }
    return Results.CONTINUE
  }

}