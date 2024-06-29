package edu.austral.dissis.twoDBoardGame.game

import edu.austral.dissis.chess.engine.chessTurn.TurnDefault
import edu.austral.dissis.chess.engine.rules.winCondition.IsCheckMate
import edu.austral.dissis.twoDBoardGame.position.Position
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
  private val turn: TurnManager = TurnDefault(Color.WHITE),
  private val rules: List<RuleManager>,
  private val winningCondition: WinCondition = IsCheckMate(),
  private val movementApplier: MovementApplier = DefaultMovApplier()
  ) {


  fun movePiece(from: Position, to: Position): MovementResult {
    val move = Movement(from, to, turn.actualTurn())

    val gameValidation = validateGameRules(move)
    if (gameValidation !is SuccessfullMovementResult) return gameValidation

    val pieceValidation = validatePieceRules(move)
    if (pieceValidation !is SuccessfullMovementResult) return pieceValidation

    val turnValidation = validateTurnRules(move)
    if (turnValidation !is SuccessfullMovementResult) return turnValidation

   if (isCheckMate(pieceValidation.game.getBoard()))
     return WinnerResult(turn.actualTurn(), pieceValidation.getGameResult())

    return pieceValidation
    }


  fun getBoard(): DefaultBoard {
    return this.board
  }

  fun getTurn(): Color{
    return this.turn.actualTurn()
  }

  fun getTurnMan(): TurnManager{
    return this.turn
  }

  fun getResetedTM(): TurnManager{
    return TurnDefault(Color.WHITE)
  }
  fun getMovementApplier(): MovementApplier{
    return this.movementApplier
  }

  fun getRules(): List<RuleManager>{
    return this.rules
  }

  fun getWinningCondition(): WinCondition{
    return this.winningCondition
  }

  fun validateGameRules (move: Movement): MovementResult {
    for (rule in rules) {
      return when (val result = rule.checkMovement(board, move)) {
        is Valid -> continue
        is Invalid -> UnsuccessfullMovementResult(result.message, this)
      }
    }
    return SuccessfullMovementResult(this)
  }

  fun validatePieceRules(move: Movement): MovementResult {
    val pieceToMove = board.getPiece(move.getFrom()) ?: throw NoSuchElementException("No piece to selected")

    return when(val result = pieceToMove.validateMovement(move, board)){
      is Valid -> makeMovement(move, board, result.getActionResult())
      is Invalid -> UnsuccessfullMovementResult(result.message, this)
    }
  }

  fun makeMovement(move: Movement, board: DefaultBoard, actions: List<ActionResult>): SuccessfullMovementResult{
    var newBoard: DefaultBoard = movementApplier.applyMovement(move, board)
        newBoard = movementApplier.executeSpecial(move, newBoard, actions)

    return SuccessfullMovementResult(
      Game(
        newBoard,
        turn.getNextTurn(move, this.board),
        rules,
        winningCondition,
        movementApplier))
  }

  fun validateTurnRules(move: Movement): MovementResult {
    return when (val result = turn.validateTurn(move, board)){
      is Valid -> SuccessfullMovementResult(this)
      is Invalid -> UnsuccessfullMovementResult(result.message, this)
    }
  }

  fun getEnemyColor(): Color{
    return when(turn.actualTurn()){
      Color.WHITE -> Color.BLACK
      Color.BLACK -> Color.WHITE
    }
  }

  fun isCheckMate(board: DefaultBoard): Boolean{
    return winningCondition.checkWinner(board, getEnemyColor(), rules)
  }

}