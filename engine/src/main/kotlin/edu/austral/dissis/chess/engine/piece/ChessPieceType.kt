package edu.austral.dissis.chess.engine.piece

import edu.austral.dissis.twoDBoardGame.piece.PieceType

enum class ChessPieceType(val string: String) : PieceType {
    PAWN("pawn"),
    ROOK("rook"),
    KNIGHT("knight"),
    BISHOP("bishop"),
    QUEEN("queen"),
    KING("king")
}