package edu.austral.dissis.chess.engine.Movement

import edu.austral.dissis.chess.engine.Piece.Position

interface Movement {
  fun move(Position: Position, amount: Int): Position
}
