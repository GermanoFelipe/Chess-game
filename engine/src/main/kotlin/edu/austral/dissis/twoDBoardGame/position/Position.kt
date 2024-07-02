package edu.austral.dissis.twoDBoardGame.position

import java.util.*

class Position(
  val row: Int,
  val column: Int) {

  override fun toString(): String {
    return "$row,$column"
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