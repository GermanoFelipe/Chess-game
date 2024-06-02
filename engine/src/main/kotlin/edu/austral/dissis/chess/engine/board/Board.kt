package edu.austral.dissis.chess.engine.board

import edu.austral.dissis.chess.engine.piece.Piece
import edu.austral.dissis.chess.engine.piece.Position

class Board (val size: Position, val pieces: Map<Position, Piece?>) {
  fun addPiece(position: Position, piece: Piece): Board {
    val newPieces = pieces + Pair(position, piece)
    return Board(size, newPieces)
  }

  fun pieceAt(position: Position): Piece? {
    return pieces[position]
  }

  fun removePiece(position: Position, piece: Piece): Board {
    val newPieces = pieces + Pair (position, null)
    return Board(size, newPieces)
  }
}
