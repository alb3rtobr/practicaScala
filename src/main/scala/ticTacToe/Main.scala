package ticTacToe

import ticTacToe.models.{Game, Play, Player1, Player2}
import ticTacToe.views.{CoordinateView, GameView, GestorIO}
import akka.actor.{ActorSystem, Props}

object Main extends App{
    var game = new Game
    val system = ActorSystem("system")
    val player2 = system.actorOf(Props[Player2], name = "player2")
    val player1 = system.actorOf(Props(new Player1(player2)), name = "player1")
    player1 ! Play(game)


}
