package edu.austral.dissis.twoDBoardGame.board

import edu.austral.dissis.twoDBoardGame.piece.Piece
import edu.austral.dissis.twoDBoardGame.position.Position

interface Board {

  fun getPiece(position: Position): Piece?

  fun getUsedPositions(): List<Position>

  fun movePiece(from: Position, to: Position): Board

  fun positionExists(position: Position): Boolean

  fun getPieces(): Map<Position, Piece>

  fun removePiece(from: Position): Board

  fun getRow(): Int

  fun getColumn(): Int
}