package edu.austral.dissis.chess.engine.board

import edu.austral.dissis.chess.engine.piece.Piece
import edu.austral.dissis.chess.engine.piece.Position

class DefaultBoard (
  val row: Int,
  val column: Int,
  //val size: Position,
  private val pieces: Map<Position, Piece>
  ) : Board {

  override fun getPieces(): Map<Position, Piece> {
    return pieces
  }

  override fun movePiece(from: Position, to: Position): DefaultBoard {
    val piece = pieces[from] ?: throw IllegalArgumentException("No piece at $from")
    val newPieces = pieces - from + Pair(to, piece)
    return DefaultBoard(8,8, newPieces)
  }

  override fun positionExists(position: Position): Boolean {
    return pieces.containsKey(position)
  }

  override fun getPiece (position: Position): Piece? {
    return pieces.get(position)
  }

  override fun getUsedPositions(): List<Position> {
    return pieces.keys.toList()
  }
}