package edu.austral.dissis.chess.engine.game

import edu.austral.dissis.chess.engine.piece.Color

class TurnDefault (
        private val turnColor: Color
        ) : TurnManager{
  override fun initialTurn(): Color {
    return Color.WHITE
  }

  override fun actualTurn(): Color {
    return turnColor
  }

  override fun nextTurn(): TurnManager {
    return if (turnColor == Color.WHITE) TurnDefault(Color.BLACK)
    else TurnDefault(Color.WHITE)
  }

  override fun nextColor(turn: Color): Color {
    return if (turn == Color.WHITE) Color.BLACK
    else Color.WHITE
  }
}