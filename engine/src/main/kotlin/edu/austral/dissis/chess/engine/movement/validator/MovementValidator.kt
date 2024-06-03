package edu.austral.dissis.chess.engine.movement.validator

import edu.austral.dissis.chess.engine.board.DefaultBoard
import edu.austral.dissis.chess.engine.piece.Position

interface MovementValidator {
  fun checkMovement(from: Position, to: Position, defaultBoard: DefaultBoard): Boolean
}