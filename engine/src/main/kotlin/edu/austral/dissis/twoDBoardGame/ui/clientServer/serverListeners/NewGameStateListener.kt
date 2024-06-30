package edu.austral.dissis.twoDBoardGame.ui.clientServer.serverListeners

import edu.austral.dissis.chess.gui.NewGameState
import edu.austral.dissis.twoDBoardGame.ui.clientServer.manager.LocalClient
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.MessageListener

class NewGameStateListener(private val gameClient: LocalClient): MessageListener<NewGameState> {
  override fun handleMessage(message: Message<NewGameState>) {
    gameClient.handleNewGameState(message.payload)
  }
}