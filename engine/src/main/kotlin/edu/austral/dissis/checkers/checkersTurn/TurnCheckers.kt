package edu.austral.dissis.checkers.checkersTurn

import edu.austral.dissis.checkers.factory.captureBackward
import edu.austral.dissis.checkers.factory.captureForward
import edu.austral.dissis.checkers.piece.CheckersPieceType
import edu.austral.dissis.twoDBoardGame.board.DefaultBoard
import edu.austral.dissis.twoDBoardGame.board.SizeOfBoard
import edu.austral.dissis.twoDBoardGame.game.Movement
import edu.austral.dissis.twoDBoardGame.game.TurnManager
import edu.austral.dissis.twoDBoardGame.piece.Color
import edu.austral.dissis.twoDBoardGame.position.Position
import edu.austral.dissis.twoDBoardGame.results.Invalid
import edu.austral.dissis.twoDBoardGame.results.RuleResult
import edu.austral.dissis.twoDBoardGame.results.Valid

class TurnCheckers (
  private val color: Color,
  private val capture: Boolean = false,
  private val previousId: String = ""
        ): TurnManager {

  private val manCaptureValidator = captureForward()
  private val kingCaptureValidator = captureBackward()

  override fun getNextTurn(move: Movement, board: DefaultBoard): TurnManager {
    return if (nextHasToBeCapture(move, board))
      createTurnWithCapture(move, board)
    else
      createTurnWithoutCapture()
  }

  override fun actualTurn(): Color {
    return color
  }

  override fun validateTurn(move: Movement, board: DefaultBoard): RuleResult {
    val piece = board.getPiece(move.getFrom()) ?: return Invalid("There is no piece in the selected position")
    return if (capture)
      if (piece.getType() == CheckersPieceType.KING
          && isKingCapture(move, board)
          && isTheSamePiece(move, board))
        Valid()

      else if (isManCapture(move, board) && isTheSamePiece(move, board))
        Valid()
      else
        Invalid("The piece has to capture")
    else
      Valid()
  }

  private fun nextHasToBeCapture(move: Movement, board: DefaultBoard): Boolean {
    return if (board.getPiece(move.getFrom())?.getType() == CheckersPieceType.MAN)
      (isManCapture(move, board) || capture) && manHasAvailableCapture(move, board)
    else (isKingCapture(move, board) || capture) && kingHasAvailableCapture(move, board)
  }

  private fun manHasAvailableCapture(move: Movement, board: DefaultBoard): Boolean {
    val boardSize = board.getSize()
    val forward = if (color == Color.WHITE) 1 else -1

    val left = Movement(
      move.getTo(),
      Position(move.getTo().row + (2 * forward), move.getTo().column - 2),
      color
    )
    if (inBounds(left, boardSize) && isManCapture(left, board)) return true

    val right = Movement(
      move.getTo(),
      Position(move.getTo().row + (2 * forward), move.getTo().column + 2),
      color
    )

    return inBounds(right, boardSize) && isManCapture(right, board)
  }


  private fun inBounds(move: Movement, board: SizeOfBoard): Boolean {
    return move.getTo().row > 0 &&
            move.getTo().row <= board.getRows() &&
            move.getTo().column > 0 &&
            move.getTo().column <= board.getColumns()
  }

  private fun isManCapture(move: Movement, board: DefaultBoard): Boolean {
    if (board.getPiece(move.getTo()) != null) return false

    return when (manCaptureValidator.checkMovement(board, move)){
      is Valid -> true
      is Invalid -> false
    }
  }

  private fun isKingCapture(move: Movement, board: DefaultBoard): Boolean{
    if (board.getPiece(move.getTo()) != null) return false

    return when(kingCaptureValidator.checkMovement(board, move)){
      is Valid -> true
      is Invalid -> false
    }
  }

  private fun kingHasAvailableCapture(move: Movement, board: DefaultBoard): Boolean {
    val boardSize = board.getSize()

    val leftForward = Movement(
      move.getTo(),
      Position(move.getTo().row -2, move.getTo().column - 2),
      color
    )

    if (inBounds(leftForward, boardSize) && isKingCapture(leftForward, board)) return true

    val rightForward = Movement(
      move.getTo(),
      Position(move.getTo().row - 2, move.getTo().column + 2),
      color
    )

    if (inBounds(rightForward, boardSize) && isKingCapture(rightForward, board)) return true

    val leftBackwards = Movement(
      move.getTo(),
      Position(move.getTo().row + 2, move.getTo().column - 2),
      color
    )

    if (inBounds(leftBackwards, boardSize) && isKingCapture(leftBackwards, board)) return true

    val rightBackwards = Movement(
      move.getTo(),
      Position(move.getTo().row + 2, move.getTo().column + 2),
      color
    )

    return inBounds(rightBackwards, boardSize) && isKingCapture(rightBackwards, board)

  }

  private fun createTurnWithCapture(move: Movement, board: DefaultBoard): TurnCheckers {
    val pieceId = board.getPiece(move.getFrom())!!.getId()
    return TurnCheckers(color, true, pieceId)
  }

  private fun createTurnWithoutCapture(): TurnCheckers {
    return TurnCheckers(
      getOppositeTeam(),
      false
    )
  }

  private fun getOppositeTeam(): Color {
    return when (color){
      Color.WHITE -> Color.BLACK
      Color.BLACK -> Color.WHITE
    }
  }

  private fun isTheSamePiece(move: Movement, board: DefaultBoard): Boolean {
    return board.getPiece(move.getFrom())?.getId() == previousId
  }
}