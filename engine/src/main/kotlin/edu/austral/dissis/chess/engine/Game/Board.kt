package edu.austral.dissis.chess.engine.Game

import edu.austral.dissis.chess.engine.Piece.Piece
import edu.austral.dissis.chess.engine.Piece.Position

class Board (val size: Position){
  val board: Map<Position, Piece?> = mapOf()

  fun getPiece(position: Position): Piece? {
    return board[position]
  }

  fun createBoard(): Map<Position, Piece?> {
    return (0 until size.getRow()).flatMap { i ->
      (0 until size.getColumn()).map { j ->
        Position(Pair(i, j)) to null
      }
    }.toMap()
  }

  fun addPiece(position: Position, piece: Piece): Board {
    val newBoard = board.plus(position to piece)
    return Board(size).apply { newBoard }
  }

  fun removePiece(position: Position): Board {
    val newBoard = board.minus(position)
    return Board(size).apply { newBoard }
  }

}