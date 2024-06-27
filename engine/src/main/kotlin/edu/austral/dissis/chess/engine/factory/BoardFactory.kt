package edu.austral.dissis.chess.engine.factory

import edu.austral.dissis.twoDBoardGame.board.DefaultBoard
import edu.austral.dissis.twoDBoardGame.board.SizeOfBoard
import edu.austral.dissis.twoDBoardGame.piece.Color
import edu.austral.dissis.twoDBoardGame.piece.Piece
import edu.austral.dissis.twoDBoardGame.position.Position

fun createDefaultBoard(): DefaultBoard {
  val map: MutableMap<Position, Piece> = mutableMapOf()
  val size: SizeOfBoard = SizeOfBoard(8,8)

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

  return DefaultBoard(size,map)
}

fun createCappablancaBoard(): DefaultBoard{
 val map: MutableMap<Position, Piece> = mutableMapOf()
 val size: SizeOfBoard = SizeOfBoard(10, 8)

 //Pawn
 for (i in 1..10){
  map[Position(2,i)] = createPawn(Color.WHITE)
  map[Position(7,i)] = createPawn(Color.BLACK)
 }

 // Rook
 map[Position(1,1)] = createRook(Color.WHITE)
 map[Position(1,10)] = createRook(Color.WHITE)
 map[Position(8,1)] = createRook(Color.BLACK)
 map[Position(8,10)] = createRook(Color.BLACK)

 // Knight
 map[Position(1,2)] = createKnight(Color.WHITE)
 map[Position(1,9)] = createKnight(Color.WHITE)
 map[Position(8,2)] = createKnight(Color.BLACK)
 map[Position(8,9)] = createKnight(Color.BLACK)

 // ArchiBishop
 map[Position(1,3)] = createArchibishop(Color.WHITE)
 map[Position(8,3)] = createArchibishop(Color.BLACK)

 //Bishop
 map[Position(1,4)] = createBishop(Color.WHITE)
 map[Position(1,7)] = createBishop(Color.WHITE)
 map[Position(8,4)] = createBishop(Color.BLACK)
 map[Position(8,7)] = createBishop(Color.BLACK)

 // Queen
 map[Position(1,5)] = createQueen(Color.WHITE)
 map[Position(8,5)] = createQueen(Color.BLACK)

 // King
 map[Position(1,6)] = createKing(Color.WHITE)
 map[Position(8,6)] = createKing(Color.BLACK)

 //Cancellor
 map[Position(1, 8)] = createCancellor(Color.WHITE)
 map[Position(8, 8)] = createCancellor(Color.BLACK)

 return DefaultBoard(size, map)
}