package edu.austral.dissis.checkers.piece

import edu.austral.dissis.twoDBoardGame.piece.PieceType

enum class CheckersPieceType(private val string: String) : PieceType {
    MAN("pawn"),
    KING("king");

    override fun string(): String {
        return string
    }
}