package edu.austral.dissis.twoDBoardGame.game

import edu.austral.dissis.chess.engine.board.DefaultBoard
import edu.austral.dissis.chess.engine.game.results.*
import edu.austral.dissis.twoDBoardGame.position.Position
import edu.austral.dissis.chess.engine.piece.Piece
import edu.austral.dissis.chess.engine.rules.ChessRuleManager
import edu.austral.dissis.twoDBoardGame.results.Invalid
import edu.austral.dissis.twoDBoardGame.results.MovementResult
import edu.austral.dissis.twoDBoardGame.results.Valid


class Game (
  val board: DefaultBoard,
  val turn: TurnManager,
  val rules: List<Rule>,
  val history: Map<Piece, List<Movement>>,
  val ruleManager: ChessRuleManager
  ) {


  fun movePiece(from: Position, to: Position): MovementResult {
    val piece = board.getPiece(from) ?: return Invalid()

    val movement = Movement(from, to, board, piece)

    when (ruleManager.checkMovement(this, movement)) {
      is Valid -> {
        val newBoard = board.movePiece(from, to)

        val newHistory = history.toMutableMap()
        newHistory[piece] = newHistory[piece]?: listOf()
        val updateMovements = newHistory[piece]!! + movement
        newHistory[piece] = updateMovements

        val changeTurn = turn.nextTurn()

        return Valid(Game(newBoard, changeTurn, newHistory, ruleManager))
      }

      is Invalid -> {
        return Invalid()
      }

     // is GameRuleBroken -> TODO()
     // is NoPieceInPosition -> TODO()
     // is PieceRuleBroken -> TODO()
    }
  }

}