package edu.austral.dissis.twoDBoardGame.rules

import edu.austral.dissis.twoDBoardGame.game.Game
import edu.austral.dissis.twoDBoardGame.results.MovementResult
import edu.austral.dissis.twoDBoardGame.game.Movement
import edu.austral.dissis.twoDBoardGame.results.RuleResult

interface RuleManager {

  fun checkMovement(game: Game, movement: Movement): RuleResult
}