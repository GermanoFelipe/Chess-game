package edu.austral.dissis.chess.engine.piece

enum class ChessPieceType(val string: String) : PieceType {
    PAWN("pawn"),
    ROOK("rook"),
    KNIGHT("knight"),
    BISHOP("bishop"),
    QUEEN("queen"),
    KING("king")
}