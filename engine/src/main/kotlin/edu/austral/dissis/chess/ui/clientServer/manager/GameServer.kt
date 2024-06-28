package edu.austral.dissis.chess.ui.clientServer.manager

import com.fasterxml.jackson.core.type.TypeReference
import edu.austral.dissis.chess.ui.clientServer.serverListeners.MoveListener

import edu.austral.dissis.chess.gui.*
import edu.austral.dissis.chess.ui.ChessEngine
import edu.austral.dissis.chess.ui.clientServer.InitializeClient
import edu.austral.dissis.chess.ui.clientServer.MoveWithTeam
import edu.austral.dissis.chess.ui.clientServer.serverListeners.RedoListener
import edu.austral.dissis.chess.ui.clientServer.serverListeners.UndoListener
import edu.austral.dissis.chess.ui.clientServer.serverListeners.NewClientListener
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.Server
import edu.austral.ingsis.clientserver.ServerBuilder

class GameServer(private val engine: ChessEngine, serverBuilder: ServerBuilder) {

    private val server: Server
    private var clients: Map<String,String> = mutableMapOf()

    init {
        server = buildServer(serverBuilder)
        server.start()
    }

    private fun buildServer(serverBuilder: ServerBuilder): Server {
        return serverBuilder
            .withPort(8080)
            .withConnectionListener(NewClientListener(this))
            .addMessageListener("move",object :TypeReference<Message<MoveWithTeam>>(){}, MoveListener(this))
            .addMessageListener("undo",object :TypeReference<Message<String>>(){}, UndoListener(this))
            .addMessageListener("redo",object :TypeReference<Message<String>>(){}, RedoListener(this))
            .build()
    }


  fun handleNewGame(clientId:String){
    if (!clients.containsValue("WHITE")){
      clients=clients.plus(Pair(clientId,"WHITE"))
    }
    else if (!clients.containsValue("BLACK")){
      clients=clients.plus(Pair(clientId,"BLACK"))
    }
    else{
      clients=clients.plus(Pair(clientId,"OBSERVER"))
    }
    server.sendMessage(clientId,Message("init", InitializeClient(engine.init() , clientId)))
  }

  fun handleMove(movementInfo: MoveWithTeam) {
           val result = engine.applyMove(movementInfo.move)
     broadcastState(result)
   }

  fun handleUndo(clientId: String){
    if (clients[clientId]=="OBSERVER"){
      server.sendMessage(clientId,Message("invalidMove",InvalidMove("You are an observer")))
      return
    }
    else{
      val result= engine.undo()
      broadcastState(result)
    }
  }

  fun handleRedo(clientId: String){
    if (clients[clientId]=="OBSERVER"){
      server.sendMessage(clientId,Message("invalidMove",InvalidMove("You are an observer")))
      return
    }
    else{
      val result = engine.redo()
      broadcastState(result)
    }
  }

    private fun broadcastState(result: MoveResult) {
        when(result){
            is NewGameState -> server.broadcast(Message("newGameState", result))
            is GameOver -> server.broadcast(Message("gameOver", result))
            is InvalidMove -> server.broadcast(Message("invalidMove", result))
        }
    }

    fun handleQuit(clientId: String){
        clients=clients.filterKeys { it!=clientId }
        if (clients.containsValue("OBSERVER")){
            val observer=clients.filterValues { it=="OBSERVER" }.keys.first()
            if (!clients.containsValue("WHITE")){
                clients=clients.filterKeys { it!=observer }
                clients=clients.plus(Pair(observer,"WHITE"))
            }
            else if (!clients.containsValue("BLACK")){
                clients=clients.filterKeys { it!=observer }
                clients=clients.plus(Pair(observer,"BLACK"))
            }
        }
    }

}