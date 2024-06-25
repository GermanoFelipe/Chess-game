package edu.austral.dissis.twoDBoardGame.game

import edu.austral.dissis.twoDBoardGame.board.DefaultBoard
import edu.austral.dissis.twoDBoardGame.piece.Color
import edu.austral.dissis.twoDBoardGame.results.RuleResult

interface TurnManager {

  fun getNextTurn(move: Movement, board: DefaultBoard): TurnManager
  fun actualTurn() : Color

  fun validateTurn(move: Movement, board: DefaultBoard): RuleResult

  fun initialTurn() : Color

  fun nextTurn() : TurnManager

  fun nextColor(turn: Color) : Color
}
