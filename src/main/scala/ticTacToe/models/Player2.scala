package ticTacToe.models

import akka.actor.Actor
import ticTacToe.views.{CoordinateView, GameView, GestorIO}

class Player2 extends Actor {

  def receive = {

    case Play(game) =>
      if (!game.isTicTacToe) {
        if (!game.isComplete) {
          val newGame = game.put(CoordinateView.read)
          GameView.write(newGame)
          sender ! Play(newGame)
        } else {
          val newGame = game.move(CoordinateView.read, CoordinateView.read)
          GameView.write(newGame)
          sender ! Play(newGame)
        }
      }else{
        GestorIO.write("... pero has perdido")
        sender ! GameEnded()
        context.stop(self)
      }

    case GameEnded =>
      GestorIO.write("Actor Player2 ended")
      context.stop(self)
  }
}

