package edu.austral.dissis.chess.engine.piece


import edu.austral.dissis.chess.engine.movement.validator.GeneralPieceRules.PieceRuleValidator

class Piece(val type: ChessPieceType,
            val pieceColor: Color,
            val hasMoveed: Boolean,
            val id: String,
            val pieceRules: PieceRuleValidator,
            ) {
  fun hasMoved(): Boolean {
    return hasMoveed
  }
}