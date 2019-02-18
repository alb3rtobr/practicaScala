package ticTacToe.models

import akka.actor.{Actor, ActorRef}
import ticTacToe.views.{CoordinateView, GameView, GestorIO}

abstract class Player1(player2:ActorRef) extends Actor {

  def receive = {

    case Play(game) =>
      if (!game.isTicTacToe) {
        if (!game.isComplete) {
          val newGame = game.put(getAvailableCoordinate(game))
          GameView.write(newGame)
          player2 ! Play(newGame)
        } else {
          val newGame = game.move(getBusyCoordinate(game), getAvailableCoordinate(game))
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

  def getAvailableCoordinate(game:Game):Coordinate
  def getBusyCoordinate(game:Game):Coordinate
}



class Player1Manual(player2:ActorRef) extends Player1(player2) {

  override def getAvailableCoordinate(game:Game):Coordinate =
    CoordinateView.read

  override def getBusyCoordinate(game:Game):Coordinate =
    CoordinateView.read
}



class Player1Auto(player2:ActorRef) extends Player1(player2) {
  override def getAvailableCoordinate(game:Game): Coordinate = {
    val coord = new Coordinate(scala.util.Random.nextInt(3), scala.util.Random.nextInt(3))
    if (game.getColor(coord) == -1) {
      coord
    } else {
      getAvailableCoordinate(game)
    }
  }

  override def getBusyCoordinate(game:Game):Coordinate = {
    val coord=new Coordinate(scala.util.Random.nextInt(3), scala.util.Random.nextInt(3))
    if (game.getColor(coord) == 0){
      coord
    }else{
      getBusyCoordinate(game)
    }

  }
}
