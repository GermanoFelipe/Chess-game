package edu.austral.dissis.chess.engine.movement.validator

import edu.austral.dissis.chess.engine.board.Board
import edu.austral.dissis.chess.engine.piece.Position

class RowDirectionValidator : MovementValidator{
    override fun checkMovement(from: Position, to: Position, board: Board): Boolean {
        return from.row != to.row && from.column == to.column
    }
}