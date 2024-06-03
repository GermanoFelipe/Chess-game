package edu.austral.dissis.chess.engine.board

import edu.austral.dissis.chess.engine.game.Turn
import edu.austral.dissis.chess.engine.piece.Piece
import edu.austral.dissis.chess.engine.piece.Position

interface Board {

  fun getSize(): Position

  fun getPiece(position: Position): Piece?

}