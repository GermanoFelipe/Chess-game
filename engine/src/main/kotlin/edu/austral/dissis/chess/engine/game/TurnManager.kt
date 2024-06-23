package edu.austral.dissis.chess.engine.game

import edu.austral.dissis.chess.engine.piece.Color

interface TurnManager {

  fun initialTurn() : Color
  fun actualTurn() : Color

  fun nextTurn() : TurnManager

  fun nextColor(turn: Color) : Color
}
