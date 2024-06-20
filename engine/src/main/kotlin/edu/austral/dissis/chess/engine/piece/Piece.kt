package edu.austral.dissis.chess.engine.piece

import edu.austral.dissis.chess.engine.movement.Movement
import edu.austral.dissis.chess.engine.movement.validator.MovementValidator

class Piece(val type: PieceType,
            val pieceColor: Color,
            val hasMoveed: Boolean,
            val movements: Movement,
            val id: String,
            val pieceValidator: MovementValidator
            ) {
  fun hasMoved(): Boolean {
    return hasMoveed
  }

  fun pieceValidator(): MovementValidator {
    return pieceValidator
  }
}