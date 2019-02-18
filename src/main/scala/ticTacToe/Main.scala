package ticTacToe

import ticTacToe.models._
import ticTacToe.views.GestorIO
import akka.actor.{ActorSystem, Props}

object Main extends App{
  var game = new Game
  val system = ActorSystem("system")
  val gameMode = GestorIO.readInt("Introduce 0 para juego manual o 1 para automatico:")

  if (gameMode == 0){

      val player2 = system.actorOf(Props(new Player2 with ManualPlayer), name = "player2")
      val player1 = system.actorOf(Props(new Player1(player2) with ManualPlayer), name = "player1")
      player1 ! Play(game)

  }else if (gameMode == 1){

      val player2 = system.actorOf(Props(new Player2 with AutomaticPlayer2), name = "player2")
      val player1 = system.actorOf(Props(new Player1(player2) with AutomaticPlayer1), name = "player1")
      player1 ! Play(game)

  }
}
