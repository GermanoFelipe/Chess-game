package edu.austral.dissis.twoDBoardGame.game.mover

import edu.austral.dissis.twoDBoardGame.board.DefaultBoard
import edu.austral.dissis.twoDBoardGame.game.Movement
import edu.austral.dissis.twoDBoardGame.results.ActionResult

interface MovementApplier {
  fun applyMovement(movement: Movement, board: DefaultBoard): DefaultBoard

  fun executeSpecial(move: Movement, board: DefaultBoard, actions: List<ActionResult>): DefaultBoard
}