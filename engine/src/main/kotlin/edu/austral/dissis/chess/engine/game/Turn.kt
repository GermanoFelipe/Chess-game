package edu.austral.dissis.chess.engine.game

import edu.austral.dissis.chess.engine.piece.Color

interface Turn {

  fun colorTurn(turnNumber: Int): Color
}
