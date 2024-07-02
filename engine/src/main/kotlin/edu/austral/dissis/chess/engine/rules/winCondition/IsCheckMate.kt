package edu.austral.dissis.chess.engine.rules.winCondition

import edu.austral.dissis.twoDBoardGame.board.DefaultBoard
import edu.austral.dissis.twoDBoardGame.game.Movement
import edu.austral.dissis.twoDBoardGame.piece.Color
import edu.austral.dissis.twoDBoardGame.position.Position
import edu.austral.dissis.twoDBoardGame.results.Valid
import edu.austral.dissis.twoDBoardGame.rules.RuleManager
import edu.austral.dissis.twoDBoardGame.winCondition.WinCondition
import java.util.NoSuchElementException

class IsCheckMate : WinCondition {
  private val check = Check()

  override fun checkWinner(board: DefaultBoard,
                           enemy: Color,
                           gameRules: List<RuleManager>): Boolean {

    val piecePositions = getEnemies(board, enemy)

    for (piecePosition in piecePositions){
      val validMoves = findALlMovements(piecePosition, board, gameRules)

      for (validMove in validMoves){
        if (notInCheck(board, validMove, enemy)){
          return false
        }
      }
    }
    return true
  }


  private fun findALlMovements(position: Position,
                               board: DefaultBoard,
                               ruleManager: List<RuleManager>): List<Movement> {

    val piece = board.getPiece(position) ?: throw NoSuchElementException("No piece in position")
    val color = piece.getColor()
    var movements = emptyList<Movement>()
    for (i in 1..board.getRow()) {
      for (j in 1..board.getColumn()) {
        val to = Position(i, j)
        val movement = Movement(position, to, color)
        if (piece.validateMovement(movement, board) is Valid && ruleManager.all {
            it.checkMovement(board, movement) is Valid
          }) {
          movements = movements.plus(movement)
        }
      }
    }
    return movements
  }



  private fun getEnemies (
    board: DefaultBoard,
    enemy: Color)  = board.getUsedPositions().filter{ position ->
    board.getPiece(position)?.getColor() == enemy }



  private fun notInCheck (board: DefaultBoard,
                          validMove: Movement,
                          enemy: Color): Boolean{
    val newBoard = board.movePiece(validMove.getFrom(), validMove.getTo())
    return !check.inCheck(newBoard, enemy)
  }

}