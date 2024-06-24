package edu.austral.dissis.twoDBoardGame.game.mover

import edu.austral.dissis.twoDBoardGame.board.Board
import edu.austral.dissis.twoDBoardGame.game.Movement

interface MovementApplier {
  fun applyMovement(movement: Movement, board: Board): Board

  fun executeSpecial(move: Movement, board: Board): Board
}