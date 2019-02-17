package ticTacToe.models

import akka.actor.{Actor, ActorRef}
import ticTacToe.views.{CoordinateView, GameView, GestorIO}

class Player1(player2:ActorRef) extends Actor {

  def receive = {

    case Play(game) =>
      if (!game.isTicTacToe) {
        if (!game.isComplete) {
          val newGame = game.put(CoordinateView.read)
          GameView.write(newGame)
          player2 ! Play(newGame)
        } else {
          val newGame = game.move(CoordinateView.read, CoordinateView.read)
          GameView.write(newGame)
          player2 ! Play(newGame)
        }
      }else{
        GestorIO.write("... pero has perdido")
        player2 ! GameEnded()
        context.stop(self)
      }

    case GameEnded =>
      GestorIO.write("Actor Player1 ended")
      context.stop(self)
  }
}

