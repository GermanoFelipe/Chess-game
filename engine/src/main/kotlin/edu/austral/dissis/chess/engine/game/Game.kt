package edu.austral.dissis.chess.engine.game

import edu.austral.dissis.chess.engine.board.Board
import edu.austral.dissis.chess.engine.game.results.*
import edu.austral.dissis.chess.engine.piece.Position
import edu.austral.dissis.chess.engine.movement.Movement
import edu.austral.dissis.chess.engine.piece.Color
import edu.austral.dissis.chess.engine.piece.Piece
import edu.austral.dissis.chess.engine.rules.RuleManager


class Game (
  val board: Board,
  val turn: Color,
  val history: Map<Piece, List<Movement>>,
  val ruleManager: RuleManager
  ) {

  fun movePiece(from: Position, to: Position): MovementResult {
    val piece = board.getPiece(from) ?: return InvalidNoPiece()
    when (ruleManager.checkMovement(from, to, board)) {
      is ValidMovement -> {
        val newBoard = board.movePiece(from, to)

        val newHistory = history.toMutableMap()
        newHistory[piece] = newHistory[piece]?: listOf()
        val updateMovements = newHistory[piece]!!.toMutableList()
        newHistory[piece] = updateMovements


        Game(newBoard, turn, newHistory, ruleManager)
        return ValidMovement()
      }

      is InvalidMovement -> {
        return InvalidMovement()
      }

      is LeavesInCheck -> {
        return LeavesInCheck()
      }

      is OccupiedSquare -> {
        return OccupiedSquare()
      }

      is InvalidNoPiece -> {
        return InvalidNoPiece()
      }
    }
  }

}