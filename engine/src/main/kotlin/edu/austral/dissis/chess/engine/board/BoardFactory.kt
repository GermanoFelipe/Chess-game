package edu.austral.dissis.chess.engine.board

import edu.austral.dissis.twoDBoardGame.board.Board
import edu.austral.dissis.twoDBoardGame.piece.Piece
import edu.austral.dissis.twoDBoardGame.position.Position

interface BoardFactory {

  fun createBoard(): Board

  fun createPieces(): Map<Position, Piece>

}