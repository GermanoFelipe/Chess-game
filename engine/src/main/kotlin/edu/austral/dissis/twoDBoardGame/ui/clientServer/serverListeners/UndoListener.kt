package edu.austral.dissis.twoDBoardGame.ui.clientServer.serverListeners

import edu.austral.dissis.twoDBoardGame.ui.clientServer.manager.GameServer
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.MessageListener

class UndoListener(private val gameServer: GameServer): MessageListener<String> {
  override fun handleMessage(message: Message<String>) {
    gameServer.handleUndo(message.payload)
  }
}