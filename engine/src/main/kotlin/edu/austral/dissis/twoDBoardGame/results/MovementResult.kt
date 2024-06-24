package edu.austral.dissis.twoDBoardGame.results

import edu.austral.dissis.twoDBoardGame.game.Game
import edu.austral.dissis.twoDBoardGame.piece.Color

sealed interface MovementResult{
}

class InvalidMovement(val message: String) : MovementResult {
}

class ValidMovement (val game: Game): MovementResult {
    fun getMessage(): String {
        return "Valid Movement"
    }
}

class WinnerResult (val winner: Color): MovementResult {
    fun getMessage(): String {
        return "Winner is ${winner.name}"
    }
}