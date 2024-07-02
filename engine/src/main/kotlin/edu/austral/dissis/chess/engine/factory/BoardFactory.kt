package edu.austral.dissis.chess.engine.factory

import edu.austral.dissis.twoDBoardGame.board.DefaultBoard
import edu.austral.dissis.twoDBoardGame.board.SizeOfBoard
import edu.austral.dissis.twoDBoardGame.piece.Color
import edu.austral.dissis.twoDBoardGame.piece.Piece
import edu.austral.dissis.twoDBoardGame.position.Position

fun createDefaultBoard(): DefaultBoard {
  val map: MutableMap<Position, Piece> = mutableMapOf()
  val sizeOfBoard = SizeOfBoard(8,8)

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

  return DefaultBoard(sizeOfBoard,map)
}

fun createCapablancaBoard(): DefaultBoard{
 val map: MutableMap<Position, Piece> = mutableMapOf()
 val size = SizeOfBoard(10, 8)

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
 map[Position(1,3)] = createArchbishop(Color.WHITE)
 map[Position(8,3)] = createArchbishop(Color.BLACK)

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

 //Chancellor
 map[Position(1, 8)] = createChancellor(Color.WHITE)
 map[Position(8, 8)] = createChancellor(Color.BLACK)

 return DefaultBoard(size, map)
}

fun createCheckMateTestBoard(): DefaultBoard{
 val map: MutableMap<Position, Piece> = mutableMapOf()
 val size = SizeOfBoard(8, 8)

 map[Position(1,5)] = createKing(Color.WHITE)

 map[Position(5,6)] = createKnight(Color.WHITE)
 map[Position(6,5)] = createPawn(Color.WHITE)
 map[Position(6,6)] = createQueen(Color.WHITE)

 map[Position(7,6)] = createPawn(Color.BLACK)
 map[Position(8,6)] = createBishop(Color.BLACK)
 map[Position(8,5)] = createKing(Color.BLACK)
 map[Position(8,4)] = createQueen(Color.BLACK)

 return DefaultBoard(size, map)
}

fun createPromotionTestBoard(): DefaultBoard{
 val map: MutableMap<Position, Piece> = mutableMapOf()
 val size = SizeOfBoard(8, 8)

 map[Position(1,5)] = createKing(Color.WHITE)
 map[Position(8,5)] = createKing(Color.BLACK)

 map[Position(7,1)] = createPawn(Color.WHITE)
 map[Position(7,2)] = createPawn(Color.WHITE)
 map[Position(7,8)] = createPawn(Color.WHITE)
 map[Position(8,1)] = createArchbishop(Color.BLACK)
 map[Position(8,2)] = createArchbishop(Color.BLACK)

 map[Position(2,1)] = createPawn(Color.BLACK)
 map[Position(2,2)] = createPawn(Color.BLACK)
 map[Position(2,8)] = createPawn(Color.BLACK)
 map[Position(1,1)] = createChancellor(Color.WHITE)
 map[Position(1,2)] = createChancellor(Color.WHITE)

 return DefaultBoard(size, map)
}