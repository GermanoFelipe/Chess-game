package edu.austral.dissis.chess.engine.game

import edu.austral.dissis.chess.engine.piece.Color

class TurnDefault (var turnNumber: Int): Turn {

  override fun colorTurn(turnNumber: Int): Color {
    return if (turnNumber % 2 == 0) Color.BLACK else Color.WHITE
  }
}