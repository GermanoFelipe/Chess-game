package edu.austral.dissis.twoDBoardGame.game

import edu.austral.dissis.twoDBoardGame.piece.Color
import edu.austral.dissis.twoDBoardGame.position.Position

data class Movement (
  private val from: Position,
  private val to: Position,
  private val turn: Color
  ) {

  fun getFrom(): Position {
    return from
  }

  fun getTo(): Position {
    return to
  }
  fun getTurn(): Color {
    return turn
  }
}