package edu.austral.dissis.chess.engine.game.results

import edu.austral.dissis.chess.engine.board.DefaultBoard

class SuccesfullMove(val defaultBoard: DefaultBoard): MovementResult{

  override fun possibleOutcome(defaultBoard: DefaultBoard): MovementResult {
    //TODO pasarle el move simulado
    return TODO("Provide the return value")
  }

}