package edu.austral.dissis.chess.engine.factory

import edu.austral.dissis.chess.engine.piece.ChessPieceType
import edu.austral.dissis.twoDBoardGame.piece.Color
import edu.austral.dissis.twoDBoardGame.piece.Piece
import edu.austral.dissis.twoDBoardGame.rules.andOrValidator.OrValidator


fun createPawn(color: Color): Piece {
  return Piece(
    ChessPieceType.PAWN,
    color,
    false,
    "",
    OrValidator(
      listOf(
        pawnMove(),
        pawnFirstMove(),
        pawnAttack(),
        crown(color)
      )
    )
  )
}

fun createRook(color: Color): Piece {
  return Piece(
    ChessPieceType.ROOK,
    color,
    false,
    "",
    OrValidator(
      listOf(
        moveInRow(),
        moveInColumn()
      )
    )
  )
}

fun createBishop(color: Color): Piece {
  return Piece(
    ChessPieceType.BISHOP,
    color,
    false,
    "",
    OrValidator(
      listOf(
        moveInDiagonal()
      )
    )
  )
}

fun createQueen(color: Color): Piece {
  return Piece(
    ChessPieceType.QUEEN,
    color,
    false,
    "",
    OrValidator(
      listOf(
        moveInRow(),
        moveInColumn(),
        moveInDiagonal()
      )
    )
  )
}

fun createKing(color: Color): Piece {
  return Piece(
    ChessPieceType.KING,
    color,
    false,
    "",
    OrValidator(
      listOf(
        move1(),
        castlingLeft(),
        castlingRight()
      )
    )
  )
}

fun createKnight(color: Color): Piece {
  return Piece(
    ChessPieceType.KNIGHT,
    color,
    false,
    "",
    OrValidator(
      listOf(
        moveInL()
      )
    )
  )
}

// variantes extras

// fun createArchibishop
// fun createCancellor