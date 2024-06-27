package edu.austral.dissis.chess.ui.clientServer.serverListeners

import edu.austral.dissis.chess.ui.clientServer.InitializeClient
import edu.austral.dissis.chess.ui.clientServer.manager.LocalClient
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.MessageListener

class InitListener(private val gameClient: LocalClient): MessageListener<InitializeClient> {
  override fun handleMessage(message: Message<InitializeClient>) {
    gameClient.handleInitialState(message.payload)
  }
}