package edu.austral.dissis.chess.engine.Movement

import edu.austral.dissis.chess.engine.Game.Board
import edu.austral.dissis.chess.engine.Piece.Piece
import edu.austral.dissis.chess.engine.Piece.Position

interface Movement {
  fun move(from: Position, to: Position, board: Board): Piece
}
