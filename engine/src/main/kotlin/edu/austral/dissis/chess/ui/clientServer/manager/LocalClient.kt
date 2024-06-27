package edu.austral.dissis.chess.ui.clientServer.manager

import com.fasterxml.jackson.core.type.TypeReference
import edu.austral.dissis.chess.ui.clientServer.serverListeners.InitListener
import edu.austral.dissis.chess.ui.clientServer.serverListeners.InvalidMoveListener
import edu.austral.dissis.chess.ui.clientServer.serverListeners.NewGameStateListener
import edu.austral.dissis.chess.gui.*
import edu.austral.dissis.chess.ui.clientServer.serverListeners.ClientListener
import edu.austral.dissis.chess.ui.clientServer.serverListeners.GameOverListener
import edu.austral.dissis.chess.ui.clientServer.InitializeClient
import edu.austral.dissis.chess.ui.clientServer.MoveWithTeam
import edu.austral.ingsis.clientserver.Client
import edu.austral.ingsis.clientserver.ClientBuilder
import edu.austral.ingsis.clientserver.Message
import javafx.application.Platform
import java.net.InetSocketAddress

class LocalClient(private val gameView: GameView, private val builder: ClientBuilder) {
  private val client: Client
  private var id: String = ""

  init {
    client = buildClient()
    gameView.addListener(ClientListener(this))
    client.connect()
  }

  private fun buildClient(): Client {
    return builder
      .withAddress(InetSocketAddress("localhost", 8080))
      .addMessageListener("init", object: TypeReference<Message<InitializeClient>>() {}, InitListener(this))
      .addMessageListener("newGameState", object: TypeReference<Message<NewGameState>>() {}, NewGameStateListener(this))
      .addMessageListener("invalidMove", object: TypeReference<Message<InvalidMove>>() {}, InvalidMoveListener(this))
      .addMessageListener("gameOver", object: TypeReference<Message<GameOver>>() {}, GameOverListener(this))
      .build()
  }

  fun handleInitialState(initialState: InitializeClient) {
    id = initialState.clientId
    Platform.runLater {
      gameView.handleInitialState(initialState.initialState)
    }
  }
  fun handleNewGameState(newGameState: NewGameState) {
    Platform.runLater {
      gameView.handleMoveResult(newGameState)
    }
  }
  fun handleInvalidMove(invalidMove: InvalidMove) {
    Platform.runLater {
      gameView.handleMoveResult(invalidMove)
    }
  }
  fun handleGameOver(gameOver: GameOver) {
    Platform.runLater {
      gameView.handleMoveResult(gameOver)
    }
  }
  fun handleMove(move: Move) {
    client.send(Message("move", MoveWithTeam(move, id)))
  }
  fun handleRedo() {
    client.send(Message("redo", id))
  }
  fun handleUndo() {
    client.send(Message("undo", id))
  }
}