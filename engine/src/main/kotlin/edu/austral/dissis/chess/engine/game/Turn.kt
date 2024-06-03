package edu.austral.dissis.chess.engine.game

import edu.austral.dissis.chess.engine.piece.Color

interface Turn {

  fun initialPlayer(): Color

  fun actualTurn(actualColor: Color) : Color

  fun nextTurn(actualColor: Color) : Color

}
