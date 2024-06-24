package edu.austral.dissis.twoDBoardGame.game.mover

import edu.austral.dissis.twoDBoardGame.board.Board
import edu.austral.dissis.twoDBoardGame.game.Movement
import edu.austral.dissis.twoDBoardGame.position.Position

class DefaultMovApplier: MovementApplier {

  override fun applyMovement(movement: Movement, board: Board): Board {
    return doMove(movement.getFrom(), movement.getTo(), board)
  }

  override fun executeSpecial(move: Movement, board: Board): Board {
    val from: Position = move.getFrom()
    val newBoard: Board = board



    return newBoard
  }

  private fun doMove(from: Position, to: Position, newBoard: Board): Board {
    return newBoard.movePiece(from, to)
  }

  private fun removePiece(posRemove: Position, newBoard: Board): Board {
    return newBoard.removePiece(posRemove)
  }

  // promotion things
}