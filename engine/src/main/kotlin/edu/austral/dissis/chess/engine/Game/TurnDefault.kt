package edu.austral.dissis.chess.engine.Game

import edu.austral.dissis.chess.engine.Piece.Color

class TurnDefault (var initialTurn: Int): Turn {

  override fun colorTurn(initialTurn: Int): Color {
    return if (initialTurn % 2 == 0) Color.BLACK else Color.WHITE //initialTurn starts in 1
  }

  override fun turnNumberUpdate() {
    initialTurn += 1
  }
}