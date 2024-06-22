package edu.austral.dissis.chess.engine.piece

import edu.austral.dissis.chess.engine.movement.piecesMovRules.DefaultMovementRules

class Piece(val type: ChessPieceType,
            val pieceColor: Color,
            val hasMoveed: Boolean,
            val id: String,
            val pieceRules: DefaultMovementRules,
            ) {
  fun hasMoved(): Boolean {
    return hasMoveed
  }
}