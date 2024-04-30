package edu.austral.dissis.chess.engine.Game

import edu.austral.dissis.chess.engine.Piece.Color

interface Turn {

  fun colorTurn(initialTurn: Int): Color

  fun turnNumberUpdate()
}
