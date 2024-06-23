package edu.austral.dissis.chess.engine.movement.validator.inPath

import edu.austral.dissis.chess.engine.board.DefaultBoard
import edu.austral.dissis.chess.engine.movement.validator.MovementValidator
import edu.austral.dissis.chess.engine.piece.Position
import kotlin.math.abs

class DiagonalNoPieceInPathValidator : MovementValidator {
  override fun checkMovement(from: Position, to: Position, defaultBoard: DefaultBoard): Boolean {
    val rowDiff = to.row - from.row
    val steps = abs(rowDiff)
    val minRow = minOf(from.row, to.row)
    val minColumn = minOf(from.column, to.column)
    for (i in 1 until steps) {
      if (defaultBoard.getPieces()[Position(minRow + i, minColumn + i)] != null) return false
    }
    return true
  }
}