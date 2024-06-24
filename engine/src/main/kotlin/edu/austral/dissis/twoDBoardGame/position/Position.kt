package edu.austral.dissis.twoDBoardGame.position

import java.util.*

class Position(val row: Int, val column: Int) {
  fun getPosition(): Position {
    return Position(row, column)
  }

  override fun toString(): String {
    return row.toString() + "," + column.toString()
  }
  override fun equals(other: Any?): Boolean {
    if (other is Position) {
      return row == other.row && column == other.column
    }
    return false
  }

  override fun hashCode(): Int {
    return Objects.hash(row, column)
  }

}