package edu.austral.dissis.chess.ui.clientserver

import edu.austral.dissis.checkers.factory.createDefaultCheckers
import edu.austral.dissis.chess.engine.factory.createDefaultChess
import edu.austral.dissis.chess.engine.factory.createNormalRules
import edu.austral.dissis.chess.engine.factory.createVariantChess
import edu.austral.dissis.chess.ui.ChessEngine
import edu.austral.dissis.chess.ui.clientServer.manager.GameServer
import edu.austral.ingsis.clientserver.netty.server.NettyServerBuilder

fun main(){
    var defaultChess = createDefaultChess()

    var defaultCheckers = createDefaultCheckers()

    var variantChess = createVariantChess()

    val engine = ChessEngine(defaultChess)
    val server = NettyServerBuilder.createDefault()
    GameServer(engine, server)
}