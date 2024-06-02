package edu.austral.dissis.chess.engine.game

import edu.austral.dissis.chess.engine.board.Board
import edu.austral.dissis.chess.engine.piece.Color
import edu.austral.dissis.chess.engine.piece.Position
import edu.austral.dissis.chess.engine.movement.Movement
import edu.austral.dissis.chess.engine.movement.validator.MovementValidator
import edu.austral.dissis.chess.engine.piece.Piece
import edu.austral.dissis.chess.engine.rules.uniques.UniquesRules
import edu.austral.dissis.chess.engine.rules.winCondition.WinCondition


class Game (val board: Board, val winConditions: List<WinCondition>, val gameType: GameType,
            val uniquesRules: List<UniquesRules>, val moveValidators: List<MovementValidator>,
            var turnNumber: Int, currentTurn: Color) { //win conditions validator, promoter

  fun movePiece(piece: Piece, from: Position, to: Position, board: Board) : Board{
    val movement = Movement()
    val possibleBoard = movement.move(from, to, board)
    val result1 = DefaultResult(possibleBoard, winConditions)
    val finalResult = result1.possibleOutcome(possibleBoard, winConditions)
    return if (finalResult == Results.WIN) {
      possibleBoard //.endgame()
    } else {
      possibleBoard
    }
  }

}