package edu.austral.dissis.chess.engine.Movement

import edu.austral.dissis.chess.engine.Game.Board
import edu.austral.dissis.chess.engine.Piece.Position

interface Movement {
  fun moveValidation(oldPosition: Position, newPosition: Position, board: Board): Boolean

  fun move(from: Position, to: Position, board: Board): Position
}
