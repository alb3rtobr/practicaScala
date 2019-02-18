package ticTacToe

import ticTacToe.models._
import ticTacToe.views.GestorIO
import akka.actor.{ActorSystem, Props}

object Main extends App{
  var game = new Game
  val system = ActorSystem("system")
  val gameMode = GestorIO.readInt("Introduce 0 para juego manual o 1 para automatico:")

  if (gameMode == 0){
      val player2 = system.actorOf(Props(new Player2Manual), name = "player2")
      val player1 = system.actorOf(Props(new Player1Manual(player2)), name = "player1")

      player1 ! Play(game)
  }else{
      val player2 = system.actorOf(Props(new Player2Auto), name = "player2")
      val player1 = system.actorOf(Props(new Player1Auto(player2)), name = "player1")
      player1 ! Play(game)

  }
}
