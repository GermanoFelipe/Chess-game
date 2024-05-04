package edu.austral.dissis.chess.engine.movement.validator

import edu.austral.dissis.chess.engine.board.Board
import edu.austral.dissis.chess.engine.piece.Position

class InBoardValidator : MovementValidator {
    override fun checkMovement(from: Position, to: Position, board: Board): Boolean {
        return from.row in 1..8 && from.column in 1..8 &&
                to.row in 1..8 && to.column in 1..8
    }
}