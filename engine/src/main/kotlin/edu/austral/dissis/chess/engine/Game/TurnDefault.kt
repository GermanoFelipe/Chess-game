package edu.austral.dissis.chess.engine.Game

import edu.austral.dissis.chess.engine.Piece.Color

class TurnDefault (var starterTurn: Int): Turn {

  override fun colorTurn(): Color {
    if (turnNumber() % 2 == 0) return Color.BLACK
    return Color.WHITE
  }

  override fun turnNumber(): Int {
    starterTurn = starterTurn + 1
    return starterTurn
  }
}