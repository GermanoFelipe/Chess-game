package edu.austral.dissis.chess.ui.clientserver

import edu.austral.dissis.chess.engine.factory.createNormalRules
import edu.austral.dissis.chess.ui.ChessEngine
import edu.austral.dissis.chess.ui.clientServer.manager.GameServer
import edu.austral.ingsis.clientserver.netty.server.NettyServerBuilder

fun main(){
    val engine = ChessEngine()
    val server = NettyServerBuilder.createDefault()
    GameServer(engine, server)
}