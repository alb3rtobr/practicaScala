package ticTacToe.models

import akka.actor.Actor
import ticTacToe.views.CoordinateView
import ticTacToe.views.{GameView, GestorIO}

abstract class Player2 extends Actor {


  def receive = {

    case Play(game) =>
      if (!game.isTicTacToe) {
        if (!game.isComplete) {
          val newGame = game.put(getAvailableCoordinate(game))
          GameView.write(newGame)
          sender ! Play(newGame)
        } else {
          val newGame = game.move(getBusyCoordinate(game), getAvailableCoordinate(game))
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
  def getAvailableCoordinate(game:Game):Coordinate
  def getBusyCoordinate(game:Game):Coordinate
}

class Player2Manual extends Player2 {
  override def getAvailableCoordinate(game:Game):Coordinate = CoordinateView.read
  override def getBusyCoordinate(game:Game):Coordinate = CoordinateView.read
}

class Player2Auto extends Player2 {
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
    if (game.getColor(coord) == 1){
      coord
    }else{
      getBusyCoordinate(game)
    }

  }
}

