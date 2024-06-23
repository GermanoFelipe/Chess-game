package edu.austral.dissis.chess.engine.board

import edu.austral.dissis.chess.engine.piece.Piece
import edu.austral.dissis.chess.engine.piece.Position

class DefaultBoard (
  val size: Position,
  private val pieces: Map<Position, Piece>
  ) : Board {

  override fun getPieces(): Map<Position, Piece> {
    return pieces
  }

  override fun movePiece(from: Position, to: Position): DefaultBoard {
    val piece = pieces[from] ?: throw IllegalArgumentException("No piece at $from")
    val newPieces = pieces - from + Pair(to, piece)
    return DefaultBoard(size, newPieces)
  }

  override fun positionExists(position: Position): Boolean {
    return pieces.containsKey(position)
  }

  override fun getPiece (position: Position): Piece? {
    return pieces[position]
  }

  override fun getUsedPositions(): List<Position> {
    return pieces.filterValues { it != null }.keys.toList()
  }
}