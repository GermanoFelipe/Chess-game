package edu.austral.dissis.twoDBoardGame.ui.clientServer.serverListeners

import edu.austral.dissis.twoDBoardGame.ui.clientServer.manager.LocalClient
import edu.austral.dissis.chess.gui.GameEventListener
import edu.austral.dissis.chess.gui.Move

class ClientListener(private val client: LocalClient):GameEventListener {
    override fun handleMove(move: Move) {
        client.handleMove(move)
    }

    override fun handleRedo() {
        client.handleRedo()
    }

    override fun handleUndo() {
        client.handleUndo()
    }
}