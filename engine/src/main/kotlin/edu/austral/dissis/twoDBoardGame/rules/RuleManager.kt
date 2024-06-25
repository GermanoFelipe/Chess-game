package edu.austral.dissis.twoDBoardGame.rules

import edu.austral.dissis.twoDBoardGame.board.DefaultBoard
import edu.austral.dissis.twoDBoardGame.game.Game
import edu.austral.dissis.twoDBoardGame.results.MovementResult
import edu.austral.dissis.twoDBoardGame.game.Movement
import edu.austral.dissis.twoDBoardGame.results.RuleResult

interface RuleManager {

  fun checkMovement(board: DefaultBoard, movement: Movement): RuleResult
}