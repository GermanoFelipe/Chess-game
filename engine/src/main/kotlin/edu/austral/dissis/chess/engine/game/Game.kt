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
  val turn: TurnManager,
  val history: Map<Piece, List<Movement>>,
  val ruleManager: RuleManager
  ) {

  val initialTurn = TurnDefault(Color.WHITE)

  fun movePiece(from: Position, to: Position): MovementResult {
    val piece = board.getPiece(from) ?: return InvalidNoPiece()

    val movement = Movement(from, to, board, piece, initialTurn)

    when (ruleManager.checkMovement(movement)) {
      is ValidMovement -> {
        val newBoard = board.movePiece(from, to)

        val newHistory = history.toMutableMap()
        newHistory[piece] = newHistory[piece]?: listOf()
        val updateMovements = newHistory[piece]!! + movement
        newHistory[piece] = updateMovements

        //var changeTurn = initialTurn.nextTurn(initialTurn.color)

        return ValidMovement(Game(newBoard, initialTurn, newHistory, ruleManager))
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