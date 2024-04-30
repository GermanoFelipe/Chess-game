package edu.austral.dissis.chess.engine.Game

import edu.austral.dissis.chess.engine.Piece.Piece
import edu.austral.dissis.chess.engine.Piece.Position

class Board (val size: Position, val pieces: Map<Position, Piece?>, val deadPieces: List<Piece>) {


  fun addPiece(position: Position, piece: Piece): Board {
    val newPieces = pieces + Pair(position, piece)
    return Board(size, newPieces, deadPieces)
  }

  fun removePiece(position: Position, piece: Piece): Board {
    val newPieces = pieces + Pair (position, null)
    val deadPieces = deadPieces + piece
    return Board(size, newPieces, deadPieces)
  }

}