package edu.austral.dissis.twoDBoardGame.game

import edu.austral.dissis.twoDBoardGame.position.Position
import edu.austral.dissis.twoDBoardGame.piece.Piece
import edu.austral.dissis.twoDBoardGame.board.Board
import edu.austral.dissis.twoDBoardGame.board.DefaultBoard
import edu.austral.dissis.twoDBoardGame.game.mover.DefaultMovApplier
import edu.austral.dissis.twoDBoardGame.game.mover.MovementApplier
import edu.austral.dissis.twoDBoardGame.results.*
import edu.austral.dissis.twoDBoardGame.rules.RuleManager
import edu.austral.dissis.twoDBoardGame.winCondition.WinCondition


class Game (
  val board: DefaultBoard,
  val turn: TurnManager,
  val rules: List<RuleManager>,
  val history: Map<Piece?, List<Movement>>,
  val winningCondition: WinCondition,
  val movementApplier: MovementApplier = DefaultMovApplier()
  ) {


  fun movePiece(from: Position, to: Position): MovementResult {
    val move = Movement(from, to, this.board, turn.actualTurn())

    val gameValidation = validateGameRules(move)
    if (gameValidation !is ValidMovement) return gameValidation

    val pieceValidation = validatePieceRules(move)
    if (pieceValidation !is ValidMovement) return pieceValidation

    val turnValidation = validateTurnRules(move)
    if (turnValidation !is ValidMovement) return turnValidation

    //checkMate

    return pieceValidation
    }

  fun validateGameRules (move: Movement): MovementResult {
    for (rule in rules) {
      return when(val result = rule.checkMovement(this, move)) {
        is Invalid -> InvalidMovement()
        is Valid -> continue
      }
    }
    return ValidMovement(this)
  }

  fun validatePieceRules(move: Movement): MovementResult {
    val pieceToMove = board.getPiece(move.getFrom()) ?: return InvalidMovement()

    return when(val result = pieceToMove.validateMovement(move, this)){
      is Valid -> makeMovement(move, board)
      is Invalid -> InvalidMovement()
    }
  }

  fun makeMovement(move: Movement, board: DefaultBoard): ValidMovement{
    val newBoard = movementApplier.applyMovement(move, board)
    val newHistory = history + (move.getBoard().getPiece(move.getFrom())
            to (history[move.getBoard().getPiece(move.getFrom())] ?: listOf()) + move)
    return ValidMovement(
      Game(newBoard, turn.nextTurn(), rules, newHistory, winningCondition, movementApplier))
  }

  fun validateTurnRules(move: Movement): MovementResult {
    return when(val result = turn.validateTurn(move, board)){
      is Valid -> ValidMovement(this)
      is Invalid -> InvalidMovement()
    }
  }

  // fun getEnemyColor
  // fun isCheckMate


}