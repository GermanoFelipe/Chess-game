package edu.austral.dissis.chess.engine.movement.validator

import edu.austral.dissis.chess.engine.board.Board
import edu.austral.dissis.chess.engine.piece.Position
import kotlin.math.abs

class DiagonalNoPieceInPathValidator : MovementValidator {
  override fun checkMovement(from: Position, to: Position, board: Board): Boolean {
    val rowDiff = to.row - from.row
    val steps = abs(rowDiff)
    val minRow = minOf(from.row, to.row)
    val minColumn = minOf(from.column, to.column)
    for (i in 1 until steps) {
      if (board.pieces[Position(minRow + i, minColumn + i)] != null) return false
    }
    return true
  }
}