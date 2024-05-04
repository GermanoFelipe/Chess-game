package edu.austral.dissis.chess.engine.movement

import edu.austral.dissis.chess.engine.board.Board
import edu.austral.dissis.chess.engine.piece.Position

class Movement {

  fun allowMovement(from: Position, to: Position, board: Board): Boolean {
    return true
  }

  fun move(from: Position, to: Position, board: Board): Board {
    val pieceToMove = board.pieces[from]
    val newPosition = Pair(to, pieceToMove)
    val pieceMoved = Pair(from, null)
    val finalPieces = board.pieces + newPosition + pieceMoved
    return Board(board.size, finalPieces)
  }
}