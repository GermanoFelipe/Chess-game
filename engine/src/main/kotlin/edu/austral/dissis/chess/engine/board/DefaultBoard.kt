package edu.austral.dissis.chess.engine.board

import edu.austral.dissis.chess.engine.piece.Piece
import edu.austral.dissis.chess.engine.piece.Position

class DefaultBoard (val size: Position, val pieces: Map<Position, Piece?>) : Board {

  fun movePiece(from: Position, to: Position): DefaultBoard {
    val piece = pieces[from] ?: throw IllegalArgumentException("No piece at $from")
    val newPieces = pieces - from + Pair(to, piece)
    return DefaultBoard(size, newPieces)
  }

  override fun getSize(): Position {
    TODO("Not yet implemented")
  }

  override fun getPiece (position: Position): Piece? {
    return pieces[position]
  }
}

// fun addPiece(position: Position, piece: Piece): Board {
//    val newPieces = pieces + Pair(position, piece)
//    return Board(size, newPieces)
//  }
//
//  fun pieceAt(position: Position): Piece? {
//    return pieces[position]
//  }
//
//  fun removePiece(position: Position, piece: Piece): Board {
//    val newPieces = pieces + Pair (position, null)
//    return Board(size, newPieces)
//  }
