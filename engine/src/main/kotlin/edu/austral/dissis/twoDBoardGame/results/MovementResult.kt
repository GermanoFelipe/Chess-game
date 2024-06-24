package edu.austral.dissis.twoDBoardGame.results

import edu.austral.dissis.twoDBoardGame.game.Game
import edu.austral.dissis.twoDBoardGame.piece.Color

sealed interface MovementResult{
    fun getMessage(): String
}

class InvalidMovement : MovementResult {
    override fun getMessage(): String {
        return "Invalid Movement"
    }
}

class ValidMovement (val game: Game): MovementResult {
    override fun getMessage(): String {
        return "Valid Movement"
    }
}

class WinnerResult (val winner: Color): MovementResult {
    override fun getMessage(): String {
        return "Winner is ${winner.name}"
    }
}