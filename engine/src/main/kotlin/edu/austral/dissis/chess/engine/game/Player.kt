package edu.austral.dissis.chess.engine.game

import edu.austral.dissis.chess.engine.piece.Color

class Player (private val color: Color) {

  fun getColor(): Color {
    return color
  }
}