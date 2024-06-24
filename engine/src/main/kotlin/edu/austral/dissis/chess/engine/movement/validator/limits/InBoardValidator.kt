package edu.austral.dissis.chess.engine.movement.validator.limits

import edu.austral.dissis.chess.engine.board.DefaultBoard
import edu.austral.dissis.chess.engine.movement.validator.GeneralPieceRules.PieceRuleValidator
import edu.austral.dissis.chess.engine.piece.Position

class InBoardValidator : PieceRuleValidator {
    override fun checkMovement(from: Position, to: Position, defaultBoard: DefaultBoard): Boolean {
        return from.row in 1..8 && from.column in 1..8 &&
                to.row in 1..8 && to.column in 1..8
    }
}