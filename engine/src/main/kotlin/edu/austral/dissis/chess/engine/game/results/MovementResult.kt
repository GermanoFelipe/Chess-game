package edu.austral.dissis.chess.engine.game.results

import edu.austral.dissis.chess.engine.board.DefaultBoard

interface MovementResult {

  fun possibleOutcome(defaultBoard: DefaultBoard): MovementResult
}