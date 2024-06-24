package edu.austral.dissis.twoDBoardGame.game.mover

import edu.austral.dissis.twoDBoardGame.board.DefaultBoard
import edu.austral.dissis.twoDBoardGame.game.Movement

interface MovementApplier {
  fun applyMovement(movement: Movement, board: DefaultBoard): DefaultBoard

  fun executeSpecial(move: Movement, board: DefaultBoard): DefaultBoard
}