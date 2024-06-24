package edu.austral.dissis.chess.engine.board

import edu.austral.dissis.twoDBoardGame.Board.Board
import edu.austral.dissis.chess.engine.piece.Piece
import edu.austral.dissis.twoDBoardGame.position.Position

interface BoardFactory {

  fun createBoard(): Board

  fun createPieces(): Map<Position, Piece>

}