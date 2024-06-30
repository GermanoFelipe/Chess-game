package edu.austral.dissis.twoDBoardGame.ui.clientServer.serverListeners

import edu.austral.dissis.chess.gui.InvalidMove
import edu.austral.dissis.twoDBoardGame.ui.clientServer.manager.LocalClient
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.MessageListener

class InvalidMoveListener(private val gameClient: LocalClient): MessageListener<InvalidMove> {
  override fun handleMessage(message: Message<InvalidMove>) {
    gameClient.handleInvalidMove(message.payload)
  }
}