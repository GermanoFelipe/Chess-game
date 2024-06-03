package edu.austral.dissis.chess.engine.board

import edu.austral.dissis.chess.engine.piece.Piece

interface BoardFactory {

  fun createBoard(): Board


}