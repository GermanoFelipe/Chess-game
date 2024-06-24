package edu.austral.dissis.chess.engine.movement.validator.limits

import edu.austral.dissis.chess.engine.board.DefaultBoard
import edu.austral.dissis.chess.engine.movement.validator.GeneralPieceRules.PieceRuleValidator
import edu.austral.dissis.twoDBoardGame.position.Position

class HasMovedLimit: PieceRuleValidator {
  override fun checkMovement(from: Position, to: Position, defaultBoard: DefaultBoard): Boolean {
    val piece = defaultBoard.getPieces()[from]
    val pieceMoved = piece!!.hasMoved() //true if moved
    if (pieceMoved) return false
    return true
  }
}