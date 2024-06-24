package edu.austral.dissis.chess.engine.factory

import edu.austral.dissis.chess.engine.board.DefaultBoard
import edu.austral.dissis.twoDBoardGame.board.Board
import edu.austral.dissis.twoDBoardGame.piece.Color
import edu.austral.dissis.twoDBoardGame.piece.Piece
import edu.austral.dissis.twoDBoardGame.position.Position

fun createNormalBoard(): Board {
  val map: MutableMap<Position, Piece> = mutableMapOf()

  // Pawn
  for (i in 1..8){
    map[Position(i,2)] = createPawn(Color.WHITE)
    map[Position(i,7)] = createPawn(Color.BLACK)
  }

  // Rook
  map[Position(1,1)] = createRook(Color.WHITE)
  map[Position(8,1)] = createRook(Color.WHITE)
  map[Position(1,8)] = createRook(Color.BLACK)
  map[Position(8,8)] = createRook(Color.BLACK)

  // Knight
  map[Position(2,1)] = createKnight(Color.WHITE)
  map[Position(7,1)] = createKnight(Color.WHITE)
  map[Position(2,8)] = createKnight(Color.BLACK)
  map[Position(7,8)] = createKnight(Color.BLACK)

  // Bishop
  map[Position(3,1)] = createBishop(Color.WHITE)
  map[Position(6,1)] = createBishop(Color.WHITE)
  map[Position(3,8)] = createBishop(Color.BLACK)
  map[Position(6,8)] = createBishop(Color.BLACK)

  // Queen
  map[Position(4,1)] = createQueen(Color.WHITE)
  map[Position(4,8)] = createQueen(Color.BLACK)

  // King
  map[Position(5,1)] = createKing(Color.WHITE)
  map[Position(5,8)] = createKing(Color.BLACK)

  return DefaultBoard(8,8,map)
}