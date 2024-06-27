package edu.austral.dissis.chess.ui.clientServer.serverListeners

import edu.austral.dissis.chess.ui.clientServer.manager.GameServer
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.MessageListener

class RedoListener(private val gameServer: GameServer): MessageListener<String>{
    override fun handleMessage(message: Message<String>) {
        gameServer.handleRedo(message.payload)
    }

}