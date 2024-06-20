package edu.austral.dissis.chess.engine.board

import edu.austral.dissis.chess.engine.piece.Piece
import edu.austral.dissis.chess.engine.piece.Position

interface Board {

  fun getPiece(position: Position): Piece?

  fun getUsedPositions(): List<Position>

  fun movePiece(from: Position, to: Position): Board

  fun positionExists(position: Position): Boolean
}