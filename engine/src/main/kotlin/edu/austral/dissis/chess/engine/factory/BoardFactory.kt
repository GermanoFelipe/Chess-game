package edu.austral.dissis.chess.engine.factory

import edu.austral.dissis.twoDBoardGame.board.DefaultBoard
import edu.austral.dissis.twoDBoardGame.board.Board
import edu.austral.dissis.twoDBoardGame.piece.Color
import edu.austral.dissis.twoDBoardGame.piece.Piece
import edu.austral.dissis.twoDBoardGame.position.Position

fun createDefaultBoard(): DefaultBoard {
  val map: MutableMap<Position, Piece> = mutableMapOf()

 // Pawn
 for (i in 1..8){
   map[Position(2,i)] = createPawn(Color.WHITE)
   map[Position(7,i)] = createPawn(Color.BLACK)
 }

  // Rook
  map[Position(1,1)] = createRook(Color.WHITE)
  map[Position(1,8)] = createRook(Color.WHITE)
  map[Position(8,1)] = createRook(Color.BLACK)
  map[Position(8,8)] = createRook(Color.BLACK)

  // Knight
  map[Position(1,2)] = createKnight(Color.WHITE)
  map[Position(1,7)] = createKnight(Color.WHITE)
  map[Position(8,2)] = createKnight(Color.BLACK)
  map[Position(8,7)] = createKnight(Color.BLACK)

  // Bishop
  map[Position(1,3)] = createBishop(Color.WHITE)
  map[Position(1,6)] = createBishop(Color.WHITE)
  map[Position(8,3)] = createBishop(Color.BLACK)
  map[Position(8,6)] = createBishop(Color.BLACK)

  // Queen
  map[Position(1,4)] = createQueen(Color.WHITE)
  map[Position(8,4)] = createQueen(Color.BLACK)

  // King
  map[Position(1,5)] = createKing(Color.WHITE)
  map[Position(8,5)] = createKing(Color.BLACK)

  return DefaultBoard(8,8,map)
}