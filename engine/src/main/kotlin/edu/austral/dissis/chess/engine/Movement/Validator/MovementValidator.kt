package edu.austral.dissis.chess.engine.Movement.Validator

import edu.austral.dissis.chess.engine.Game.Board
import edu.austral.dissis.chess.engine.Piece.Position

interface MovementValidator {
  fun checkMovement(from: Position, to: Position, board: Board): Boolean
}