package edu.austral.dissis.chess.engine.game

import edu.austral.dissis.chess.engine.board.Board
import edu.austral.dissis.chess.engine.piece.Color
import edu.austral.dissis.chess.engine.piece.Position
import edu.austral.dissis.chess.engine.movement.Movement
import edu.austral.dissis.chess.engine.piece.Piece


class Game (val board: Board, val gameType: GameType,
            var turnNumber: Int, currentTurn: Color) { //win conditions, promoter

  fun movePiece(piece: Piece) : Board{
    piece.hasMoved()
    return board
  }

}