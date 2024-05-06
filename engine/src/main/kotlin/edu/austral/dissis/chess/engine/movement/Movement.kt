package edu.austral.dissis.chess.engine.movement

import edu.austral.dissis.chess.engine.board.Board
import edu.austral.dissis.chess.engine.piece.Position

class Movement {

  fun allowMovement(from: Position, to: Position, board: Board): Boolean {
    val pieceToMove = board.pieces[from]
    return if (pieceToMove != null) {
      pieceToMove.pieceValidator.checkMovement(from, to, board)
    } else false
  }

  fun move(from: Position, to: Position, board: Board): Board {
    if (allowMovement(from, to, board) == true) {
      val pieceToMove = board.pieces[from]
      val newPosition = Pair(to, pieceToMove)
      val pieceMoved = Pair(from, null)
      val finalPieces = board.pieces + newPosition + pieceMoved
      return Board(board.size, finalPieces)
    }
    return board
  }
}