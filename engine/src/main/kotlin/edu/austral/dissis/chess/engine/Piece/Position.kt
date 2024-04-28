package edu.austral.dissis.chess.engine.Piece

class Position (val pair: Pair<Int, Int>) {
  fun getRow(): Int {
    return pair.first
  }

  fun getColumn(): Int {
    return pair.second
  }

  fun getPosition(): Pair<Int, Int> {
    return pair
  }
}