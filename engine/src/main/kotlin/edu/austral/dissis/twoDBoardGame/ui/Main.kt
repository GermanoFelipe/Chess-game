package edu.austral.dissis.twoDBoardGame.ui


import edu.austral.dissis.checkers.factory.createDefaultCheckers
import edu.austral.dissis.chess.engine.factory.createDefaultChess
import edu.austral.dissis.chess.engine.factory.createVariantChess
import edu.austral.dissis.chess.gui.CachedImageResolver
import edu.austral.dissis.chess.gui.DefaultImageResolver
import edu.austral.dissis.chess.gui.SimpleGameEngine
import edu.austral.dissis.chess.gui.createGameViewFrom
import javafx.application.Application
import javafx.application.Application.launch
import javafx.scene.Scene
import javafx.stage.Stage


fun main() {
    launch(ChessGameApplication::class.java)
}

private var defaultChess = createDefaultChess()

private var defaultCheckers = createDefaultCheckers()

private var variantChess = createVariantChess()


class ChessGameApplication : Application() {
    private val gameEngine = ChessEngine(defaultChess)
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
