package edu.austral.dissis.chess.engine.rules

import edu.austral.dissis.chess.engine.board.Board
import edu.austral.dissis.chess.engine.game.results.MovementResult
import edu.austral.dissis.chess.engine.piece.Piece
import edu.austral.dissis.chess.engine.piece.Position

interface RuleManager {

  fun checkMovement(from: Position, to: Position, defaultBoard: Board): MovementResult
}