package edu.austral.dissis.chess.engine.movement.validator

import edu.austral.dissis.chess.engine.board.Board
import edu.austral.dissis.chess.engine.piece.Position

class OrValidator(val validator1: MovementValidator, val validator2: MovementValidator)
  : MovementValidator {
    override fun checkMovement(from: Position, to: Position, board: Board): Boolean {
        return validator1.checkMovement(from, to, board) || validator2.checkMovement(from, to, board)
    }
}