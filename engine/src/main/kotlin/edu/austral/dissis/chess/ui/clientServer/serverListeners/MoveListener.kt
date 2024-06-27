package edu.austral.dissis.chess.ui.clientServer.serverListeners

import edu.austral.dissis.chess.ui.clientServer.manager.GameServer
import edu.austral.dissis.chess.ui.clientServer.MoveWithTeam
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.MessageListener

class MoveListener(private val gameServer: GameServer):MessageListener<MoveWithTeam> {
  override fun handleMessage(message: Message<MoveWithTeam>) {
    gameServer.handleMove(message.payload)
  }
}