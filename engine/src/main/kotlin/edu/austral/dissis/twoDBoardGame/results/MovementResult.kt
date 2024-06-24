package edu.austral.dissis.twoDBoardGame.results

import edu.austral.dissis.twoDBoardGame.game.Game
import edu.austral.dissis.twoDBoardGame.piece.Color

sealed interface MovementResult{
}

class UnsuccessfullMovementResult(val message: String) : MovementResult {
}

class SuccessfullMovementResult (val game: Game): MovementResult {
}

class WinnerResult (val winner: Color): MovementResult {
}