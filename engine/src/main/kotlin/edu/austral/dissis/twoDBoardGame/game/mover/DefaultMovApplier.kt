package edu.austral.dissis.twoDBoardGame.game.mover

import edu.austral.dissis.twoDBoardGame.board.DefaultBoard
import edu.austral.dissis.twoDBoardGame.game.Movement
import edu.austral.dissis.twoDBoardGame.position.Position

class DefaultMovApplier: MovementApplier {

  override fun applyMovement(movement: Movement, board: DefaultBoard): DefaultBoard {
    return doMove(movement.getFrom(), movement.getTo(), board)
  }

  override fun executeSpecial(move: Movement, board: DefaultBoard): DefaultBoard {
    val from: Position = move.getFrom()
    val newBoard: DefaultBoard = board



    return newBoard
  }

  private fun doMove(from: Position, to: Position, newBoard: DefaultBoard): DefaultBoard {
    return newBoard.movePiece(from, to)
  }

  private fun removePiece(posRemove: Position, newBoard: DefaultBoard): DefaultBoard {
    return newBoard.removePiece(posRemove)
  }

  // promotion things
}