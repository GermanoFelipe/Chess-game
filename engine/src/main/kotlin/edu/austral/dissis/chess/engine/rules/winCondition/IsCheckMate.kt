package edu.austral.dissis.chess.engine.rules.winCondition

import edu.austral.dissis.twoDBoardGame.board.Board
import edu.austral.dissis.twoDBoardGame.board.DefaultBoard
import edu.austral.dissis.twoDBoardGame.game.Game
import edu.austral.dissis.twoDBoardGame.game.Movement
import edu.austral.dissis.twoDBoardGame.piece.Color
import edu.austral.dissis.twoDBoardGame.position.Position
import edu.austral.dissis.twoDBoardGame.results.Valid
import edu.austral.dissis.twoDBoardGame.rules.RuleManager
import edu.austral.dissis.twoDBoardGame.winCondition.WinCondition
import java.util.NoSuchElementException

class IsCheckMate : WinCondition {
  val check = Check()
  override fun checkWinner(board: DefaultBoard, color: Color, gameRules: List<RuleManager>, game: Game): Boolean {
    val piecePositions = getEnemies(board, color)

    for (piecePosition in piecePositions){
      val validMoves = findALlMovements(piecePosition, board, gameRules, game)

      for (validMove in validMoves){
        if (notInCheck(board, validMove, color, game)){
          return false
        }
      }
    }
    return true
  }


  fun findALlMovements(position: Position, board: DefaultBoard,
                       ruleManager: List<RuleManager>, game: Game): List<Movement> {
    val piece = board.getPiece(position) ?: throw NoSuchElementException("No piece in position")
    val color = piece.pieceColor
    var movements = emptyList<Movement>()
    for (i in 1..board.getRow()) {
      for (j in 0..board.getColumn()) {
        val to = Position(i, j)
        val movement = Movement(position, to, board, color)
        if (piece.validateMovement(movement, game) is Valid && ruleManager.all {
            it.checkMovement(game, movement) is Valid
          }) {
          movements = movements.plus(movement)
        }
      }
    }
    return movements
  }



  fun getEnemies (
    board: DefaultBoard,
    enemy: Color) : List<Position> {

    val usingPositions = board.getUsedPositions()

    return usingPositions.filter { position -> board.getPiece(position)?.pieceColor == enemy }
  }

  fun notInCheck (board: DefaultBoard, validMove: Movement, enemy: Color, game: Game): Boolean{
    val newBoard = board.movePiece(validMove.getFrom(), validMove.getTo())
    return !check.inCheck(game, newBoard, enemy)
  }

}