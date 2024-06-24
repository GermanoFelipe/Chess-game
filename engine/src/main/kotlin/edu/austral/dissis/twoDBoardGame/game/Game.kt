package edu.austral.dissis.twoDBoardGame.game

import edu.austral.dissis.chess.engine.chessTurn.TurnDefault
import edu.austral.dissis.chess.engine.rules.winCondition.IsCheckMate
import edu.austral.dissis.twoDBoardGame.position.Position
import edu.austral.dissis.twoDBoardGame.piece.Piece
import edu.austral.dissis.twoDBoardGame.board.DefaultBoard
import edu.austral.dissis.twoDBoardGame.game.mover.DefaultMovApplier
import edu.austral.dissis.twoDBoardGame.game.mover.MovementApplier
import edu.austral.dissis.twoDBoardGame.piece.Color
import edu.austral.dissis.twoDBoardGame.results.*
import edu.austral.dissis.twoDBoardGame.rules.RuleManager
import edu.austral.dissis.twoDBoardGame.winCondition.WinCondition
import java.util.NoSuchElementException


class Game (
  private var board: DefaultBoard,
  val turn: TurnManager = TurnDefault(Color.WHITE),
  val rules: List<RuleManager>,
  val history: Map<Piece?, List<Movement>>,
  val winningCondition: WinCondition = IsCheckMate(),
  val movementApplier: MovementApplier = DefaultMovApplier()
  ) {


  fun movePiece(from: Position, to: Position): MovementResult {
    val move = Movement(from, to, this.board, turn.actualTurn())

    val gameValidation = validateGameRules(move)
    if (gameValidation !is SuccessfullMovementResult) return gameValidation

    val pieceValidation = validatePieceRules(move)
    if (pieceValidation !is SuccessfullMovementResult) return pieceValidation

    val turnValidation = validateTurnRules(move)
    if (turnValidation !is SuccessfullMovementResult) return turnValidation

    if (isCheckMate(pieceValidation.game.getBoard()))
      return WinnerResult(turn.actualTurn())

    return pieceValidation
    }


  fun getBoard(): DefaultBoard {
    return this.board
  }

  fun validateGameRules (move: Movement): MovementResult {
    for (rule in rules) {
      return when (val result = rule.checkMovement(this, move)) {
        is Invalid -> UnsuccessfullMovementResult(result.message)
        is Valid -> continue
      }
    }
    return SuccessfullMovementResult(this)
  }

  fun validatePieceRules(move: Movement): MovementResult {
    val pieceToMove = board.getPiece(move.getFrom()) ?: throw NoSuchElementException("No piece to selected")

    return when(val result = pieceToMove.validateMovement(move, this)){
      is Valid -> makeMovement(move, board, result.getActionResult())
      is Invalid -> UnsuccessfullMovementResult(result.message)
    }
  }

  fun makeMovement(move: Movement, board: DefaultBoard, actions: List<ActionResult>): SuccessfullMovementResult{
    var newBoard: DefaultBoard = movementApplier.applyMovement(move, board)
        newBoard = movementApplier.executeSpecial(move, newBoard, actions)

    return SuccessfullMovementResult(
      Game(
        newBoard,
        turn.nextTurn(),
        rules,
        history,
        winningCondition,
        movementApplier))
  }

  fun validateTurnRules(move: Movement): MovementResult {
    return when (val result = turn.validateTurn(move, board)){
      is Valid -> SuccessfullMovementResult(this)
      is Invalid -> UnsuccessfullMovementResult(result.message)
    }
  }

  fun getEnemyColor(): Color{
    return when(turn.actualTurn()){
      Color.WHITE -> Color.BLACK
      Color.BLACK -> Color.WHITE
    }
  }

  fun isCheckMate(board: DefaultBoard): Boolean{
    return winningCondition.checkWinner(board, getEnemyColor(), rules, this)
  }

}