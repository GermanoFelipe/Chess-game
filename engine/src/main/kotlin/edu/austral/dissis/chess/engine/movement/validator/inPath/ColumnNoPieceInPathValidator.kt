package edu.austral.dissis.chess.engine.movement.validator.inPath

import edu.austral.dissis.chess.engine.board.DefaultBoard
import edu.austral.dissis.chess.engine.movement.validator.GeneralPieceRules.PieceRuleValidator
import edu.austral.dissis.twoDBoardGame.position.Position

class ColumnNoPieceInPathValidator : PieceRuleValidator {
  override fun checkMovement(from: Position, to: Position, defaultBoard: DefaultBoard): Boolean {
      val min = minOf(from.row, to.row)
      val max = maxOf(from.row, to.row)
      for (i in min+1 until max) {
        if (defaultBoard.getPieces()[Position(i, from.column)] != null) return false
      }
      return true
  }
}