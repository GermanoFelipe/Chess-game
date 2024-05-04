package edu.austral.dissis.chess.engine.movement.validator

import edu.austral.dissis.chess.engine.board.Board
import edu.austral.dissis.chess.engine.piece.Position

interface MovementValidator {
  fun checkMovement(from: Position, to: Position, board: Board): Boolean
}