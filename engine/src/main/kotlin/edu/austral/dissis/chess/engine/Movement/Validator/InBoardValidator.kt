package edu.austral.dissis.chess.engine.Movement.Validator

import edu.austral.dissis.chess.engine.Game.Board
import edu.austral.dissis.chess.engine.Piece.Position

class InBoardValidator : MovementValidator {
    override fun checkMovement(from: Position, to: Position, board: Board): Boolean {
        return from.getRow() in 0..7 && from.getColumn() in 0..7 &&
                to.getRow() in 0..7 && to.getColumn() in 0..7
    }

}