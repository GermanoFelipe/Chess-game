package edu.austral.dissis.chess.ui.clientServer

import edu.austral.dissis.chess.gui.InitialState
import edu.austral.dissis.chess.gui.Move

data class MoveWithTeam(val move: Move, val clientId: String)

data class InitializeClient(val initialState: InitialState, val clientId: String)