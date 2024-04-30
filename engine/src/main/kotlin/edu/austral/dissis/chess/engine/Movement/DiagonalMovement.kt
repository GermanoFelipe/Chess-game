package edu.austral.dissis.chess.engine.Movement
import edu.austral.dissis.chess.engine.Game.Board
import edu.austral.dissis.chess.engine.Piece.Position
import edu.austral.dissis.chess.engine.Movement.Validator.InBoardValidator
import edu.austral.dissis.chess.engine.Piece.Piece

class DiagonalMovement : Movement {
  private val inBoardValidator = InBoardValidator()
  override fun move(from: Position, to: Position, board: Board): Piece {
    if (InBoardValidator.checkMovement(from, to, board)) {
      }

    return Piece()
    }
  }

}