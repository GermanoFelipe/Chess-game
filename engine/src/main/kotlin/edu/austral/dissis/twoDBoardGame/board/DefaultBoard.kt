package edu.austral.dissis.twoDBoardGame.board

import edu.austral.dissis.twoDBoardGame.piece.Piece
import edu.austral.dissis.twoDBoardGame.position.Position

class DefaultBoard (
  private val boardSize: SizeOfBoard = SizeOfBoard(8,8),
  private val pieces: Map<Position, Piece>
  ) : Board {

  override fun getPieces(): Map<Position, Piece> {
    return pieces
  }

  override fun movePiece(from: Position, to: Position): DefaultBoard {
    val piece = pieces[from] ?: throw IllegalArgumentException("No piece at $from")

    return DefaultBoard(boardSize, pieces + Pair(to, piece.copy(
      hasMoved = true,
      id = piece.getId()
    )) - from)
  }

  override fun positionExists(position: Position): Boolean {
    return pieces.containsKey(position)
  }

  override fun getPiece (position: Position): Piece? {
    return pieces[position]
  }

  override fun getUsedPositions(): List<Position> {
    return pieces.keys.toList()
  }

  override fun removePiece(from: Position): DefaultBoard {
    return DefaultBoard(boardSize, pieces - from)
  }

  override fun getRow(): Int {
    return boardSize.getRows()
  }

  override fun getColumn(): Int {
    return boardSize.getColumns()
  }

  override fun addPiece(position: Position, piece: Piece): DefaultBoard {
    return DefaultBoard(boardSize, pieces + Pair(position, piece))
  }

  override fun hasPosition(position: Position): Boolean{
    return pieces.containsKey(position)
  }

  override fun getSize(): SizeOfBoard {
    return boardSize
  }

  override fun getPositions(): Map<Position, Piece>{
    return pieces
  }

  override fun inBounds(position: Position): Boolean {
    return position.row in 1..boardSize.getRows() && position.column in 1..boardSize.getColumns()
  }
}