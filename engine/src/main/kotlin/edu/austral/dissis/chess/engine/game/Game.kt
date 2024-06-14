package edu.austral.dissis.chess.engine.game

import edu.austral.dissis.chess.engine.board.Board
import edu.austral.dissis.chess.engine.board.DefaultBoard
import edu.austral.dissis.chess.engine.game.results.MovementResult
import edu.austral.dissis.chess.engine.piece.Position
import edu.austral.dissis.chess.engine.movement.Movement
import edu.austral.dissis.chess.engine.piece.Piece
import edu.austral.dissis.chess.engine.rules.RuleManager


class Game (val board: Board, val turn: Turn, val history: Map<Piece, List<Movement>>,
            val ruleManager: RuleManager) {

  fun movePiece(from: Position, to: Position): MovementResult {
    //TODO
    return TODO("Provide the return value")
  }

}