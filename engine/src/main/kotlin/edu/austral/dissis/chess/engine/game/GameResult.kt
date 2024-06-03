package edu.austral.dissis.chess.engine.game

import edu.austral.dissis.chess.engine.board.DefaultBoard
import edu.austral.dissis.chess.engine.rules.winCondition.WinCondition

interface GameResult {

  fun possibleOutcome(defaultBoard: DefaultBoard, winConditions: List<WinCondition>): Results
}