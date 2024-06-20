package edu.austral.dissis.chess.engine.game

import edu.austral.dissis.chess.engine.piece.Color

interface TurnManager {

  fun actualTurn(actualColor: Color) : Color

  fun nextTurn(actualColor: Color) : TurnManager

}
