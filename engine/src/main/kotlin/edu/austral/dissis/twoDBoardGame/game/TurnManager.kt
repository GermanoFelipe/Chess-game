package edu.austral.dissis.twoDBoardGame.game

import edu.austral.dissis.twoDBoardGame.board.Board
import edu.austral.dissis.twoDBoardGame.piece.Color
import edu.austral.dissis.twoDBoardGame.results.RuleResult

interface TurnManager {

  fun initialTurn() : Color
  fun actualTurn() : Color

  fun nextTurn() : TurnManager

  fun nextColor(turn: Color) : Color

  fun validateTurn(move: Movement, board: Board): RuleResult
}
