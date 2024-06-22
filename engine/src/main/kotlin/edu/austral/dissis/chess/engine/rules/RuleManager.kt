package edu.austral.dissis.chess.engine.rules

import edu.austral.dissis.chess.engine.board.Board
import edu.austral.dissis.chess.engine.game.results.MovementResult
import edu.austral.dissis.chess.engine.movement.Movement
import edu.austral.dissis.chess.engine.piece.Piece
import edu.austral.dissis.chess.engine.piece.Position

interface RuleManager {

  fun checkMovement(movement: Movement): MovementResult
}