package edu.austral.dissis.chess.engine.movement.validator.inPath

import edu.austral.dissis.chess.engine.board.DefaultBoard
import edu.austral.dissis.chess.engine.movement.validator.MovementValidator
import edu.austral.dissis.chess.engine.piece.Position

class RowNoPieceInPathValidator : MovementValidator {
  override fun checkMovement(from: Position, to: Position, defaultBoard: DefaultBoard): Boolean {
    val min = minOf(from.column, to.column)
    val max = maxOf(from.column, to.column)
    for (i in min until max) {
      if (defaultBoard.pieces[Position(i, from.row)] != null) return false
    }
    return true
  }
}