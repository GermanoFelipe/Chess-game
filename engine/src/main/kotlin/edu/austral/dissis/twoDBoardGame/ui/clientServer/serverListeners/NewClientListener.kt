package edu.austral.dissis.twoDBoardGame.ui.clientServer.serverListeners

import edu.austral.dissis.twoDBoardGame.ui.clientServer.manager.GameServer
import edu.austral.ingsis.clientserver.ServerConnectionListener

class NewClientListener(private val GameServer: GameServer) : ServerConnectionListener {
  override fun handleClientConnection(clientId: String) {
    GameServer.handleNewGame(clientId)
  }

  override fun handleClientConnectionClosed(clientId: String) {
    TODO("Not yet implemented")
  }
}