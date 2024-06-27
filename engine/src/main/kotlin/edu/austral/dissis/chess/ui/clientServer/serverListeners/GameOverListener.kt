package edu.austral.dissis.chess.ui.clientServer.serverListeners

import edu.austral.dissis.chess.ui.clientServer.manager.LocalClient
import edu.austral.dissis.chess.gui.GameOver
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.MessageListener

class GameOverListener(private val gameClient: LocalClient): MessageListener<GameOver> {
    override fun handleMessage(message: Message<GameOver>) {
        gameClient.handleGameOver(message.payload)
    }
}