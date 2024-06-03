package edu.austral.dissis.chess.engine.movement.validator.andOrValidator

import edu.austral.dissis.chess.engine.board.DefaultBoard
import edu.austral.dissis.chess.engine.movement.validator.MovementValidator
import edu.austral.dissis.chess.engine.piece.Position

class OrValidator(val validator1: MovementValidator, val validator2: MovementValidator)
  : MovementValidator {
    override fun checkMovement(from: Position, to: Position, defaultBoard: DefaultBoard): Boolean {
        return validator1.checkMovement(from, to, defaultBoard) || validator2.checkMovement(from, to, defaultBoard)
    }
}