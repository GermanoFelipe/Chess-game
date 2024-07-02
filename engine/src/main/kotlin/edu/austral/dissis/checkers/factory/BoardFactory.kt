package edu.austral.dissis.checkers.factory

import edu.austral.dissis.twoDBoardGame.board.DefaultBoard
import edu.austral.dissis.twoDBoardGame.board.SizeOfBoard
import edu.austral.dissis.twoDBoardGame.piece.Color
import edu.austral.dissis.twoDBoardGame.piece.Piece
import edu.austral.dissis.twoDBoardGame.position.Position

fun createCheckersBoard(): DefaultBoard {
  val map: MutableMap<Position, Piece> = mutableMapOf()
  val size = SizeOfBoard(8, 8)

  for (i in 1..3) {
    val startingColumn = if (i % 2 == 1) 2 else 1
    for (j in startingColumn..8 step 2) {
      map[Position(i, j)] = createMan(Color.WHITE)
    }
  }

  for (i in 6..8) {
    val startingColumn = if (i % 2 == 1) 2 else 1
    for (j in startingColumn..8 step 2) {
      map[Position(i, j)] = createMan(Color.BLACK)
    }
  }

  return DefaultBoard(size, map)
}

fun createEatAllBoard(): DefaultBoard{
  val map: MutableMap<Position, Piece> = mutableMapOf()
  val sizeOfBoard = SizeOfBoard(8, 8)

  map[Position(1, 1)] = createMan(Color.WHITE)
  map[Position(2, 2)] = createMan(Color.BLACK)
  map[Position(4, 4)] = createMan(Color.BLACK)
  return DefaultBoard(sizeOfBoard, map)
}

fun createKingTestBoard(): DefaultBoard{
  val map: MutableMap<Position, Piece> = mutableMapOf()
  val sizeOfBoard = SizeOfBoard(8, 8)

  map[Position(3, 3)] = createMan(Color.BLACK)
  map[Position(5, 5)] = createMan(Color.BLACK)
  map[Position(6, 6)] = createKing(Color.WHITE)
  return DefaultBoard(sizeOfBoard, map)
}