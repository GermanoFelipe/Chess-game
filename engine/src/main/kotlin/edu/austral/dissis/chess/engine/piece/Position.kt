package edu.austral.dissis.chess.engine.piece

class Position(val row: Int, val column: Int) {
  fun getPosition(): Position {
    return Position(row, column)
  }
}