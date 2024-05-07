package edu.austral.dissis.chess.engine.game

import edu.austral.dissis.chess.engine.board.Board
import edu.austral.dissis.chess.engine.movement.validator.MovementValidator
import edu.austral.dissis.chess.engine.piece.Position
import edu.austral.dissis.chess.engine.rules.winCondition.WinCondition

interface GameResult {

  fun possibleOutcome(board: Board, winConditions: List<WinCondition>): Results
}