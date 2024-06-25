package edu.austral.dissis.checkers.factory

import edu.austral.dissis.checkers.piece.CheckersPieceType
import edu.austral.dissis.twoDBoardGame.piece.Color
import edu.austral.dissis.twoDBoardGame.piece.Piece
import edu.austral.dissis.twoDBoardGame.rules.andOrValidator.OrValidator

fun createMan(color: Color): Piece {
  return Piece(
    type = CheckersPieceType.MAN,
    pieceColor = color,
    hasMoved = false,
    id = "",
    OrValidator(
      listOf(
        crown(color),
        singleDiagonalFoward(),
        captureFoward()
      )
    )
  )
}

fun createKing(color: Color): Piece{
  return Piece(
    type = CheckersPieceType.KING,
    pieceColor = color,
    hasMoved = false,
    id = "",
    OrValidator(
      listOf(
        singleDiagonal(),
        captureFoward(),
        captureBackward()
      )
    )
  )
}