package edu.austral.dissis.chess.ui.clientServer

import edu.austral.dissis.chess.ui.clientServer.manager.LocalClient
import edu.austral.dissis.chess.gui.CachedImageResolver
import edu.austral.dissis.chess.gui.DefaultImageResolver
import edu.austral.dissis.chess.gui.GameView
import edu.austral.ingsis.clientserver.netty.client.NettyClientBuilder
import javafx.application.Application
import javafx.application.Application.launch
import javafx.scene.Scene
import javafx.stage.Stage


fun main(){
  launch(ChessClientGameApplication::class.java)
}

class ChessClientGameApplication : Application() {
  private val imageResolver=CachedImageResolver(DefaultImageResolver())
  private val root=GameView(imageResolver)
  private val builder=NettyClientBuilder.createDefault()

  override fun start(primaryStage: Stage){
    LocalClient(root, builder)
    primaryStage.title="Client"
    primaryStage.scene= Scene(root)
    primaryStage.show()
  }
  override fun stop(){
  }
}