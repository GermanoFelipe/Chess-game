package edu.austral.dissis.chess.engine.game

import edu.austral.dissis.chess.engine.piece.Color

class TurnDefault () : TurnManager{
  override fun actualTurn(actualColor: Color): Color {
    return if (actualColor == Color.WHITE) Color.WHITE
    else Color.BLACK
  }

  override fun nextTurn(actualColor: Color): TurnManager {
    return if (actualColor == Color.WHITE) Color.BLACK
    else Color.WHITE
  }
}