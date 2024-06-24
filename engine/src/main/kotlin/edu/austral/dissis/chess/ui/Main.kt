package edu.austral.dissis.chess.ui

import edu.austral.dissis.chess.engine.board.BoardFactory
import edu.austral.dissis.chess.engine.board.DefaultBoard
import edu.austral.dissis.chess.engine.factory.createNormalBoard
import edu.austral.dissis.twoDBoardGame.game.Game
import edu.austral.dissis.chess.engine.game.TurnDefault
import edu.austral.dissis.twoDBoardGame.piece.Color
import edu.austral.dissis.chess.engine.winCondition.CheckMate
import edu.austral.dissis.chess.gui.CachedImageResolver
import edu.austral.dissis.chess.gui.DefaultImageResolver
import edu.austral.dissis.chess.gui.SimpleGameEngine
import edu.austral.dissis.chess.gui.createGameViewFrom
import edu.austral.dissis.twoDBoardGame.game.mover.DefaultMovApplier
import edu.austral.dissis.twoDBoardGame.rules.RuleManager
import javafx.application.Application
import javafx.application.Application.launch
import javafx.scene.Scene
import javafx.stage.Stage


fun main() {
    launch(ChessGameApplication::class.java)
}


val board = createNormalBoard()
val turn = TurnDefault(Color.WHITE)
val rules = mutableListOf<RuleManager>()
val winCondition = CheckMate()
val movementApplier = DefaultMovApplier()


val game = Game(board, turn, rules, emptyMap(), winCondition, movementApplier)
class ChessGameApplication : Application() {
    private val gameEngine = ChessEngine(game)
    private val gameEngineDummy = SimpleGameEngine()
    private val imageResolver = CachedImageResolver(DefaultImageResolver())

    companion object {
        const val GameTitle = "Chess"
    }

    override fun start(primaryStage: Stage) {
        primaryStage.title = GameTitle

        val root = createGameViewFrom(gameEngine, imageResolver)

        primaryStage.scene = Scene(root)

        primaryStage.show()
    }
}
