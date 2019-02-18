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



trait ManualPlayer {

  def getAvailableCoordinate(game:Game):Coordinate =
    CoordinateView.read

  def getBusyCoordinate(game:Game):Coordinate =
    CoordinateView.read
}



trait AutomaticPlayer{
  val playerColor:Integer = -2
  val emptyColor:Integer = -1

  def getAvailableCoordinate(game:Game): Coordinate = {
    val coord = new Coordinate(scala.util.Random.nextInt(3), scala.util.Random.nextInt(3))
    if (game.getColor(coord) == emptyColor) {
      coord
    } else {
      getAvailableCoordinate(game)
    }
  }

  def getBusyCoordinate(game:Game):Coordinate = {
    val coord=new Coordinate(scala.util.Random.nextInt(3), scala.util.Random.nextInt(3))
    if (game.getColor(coord) == playerColor){
      coord
    }else{
      getBusyCoordinate(game)
    }

  }
}

trait AutomaticPlayer1 extends AutomaticPlayer{
  override val playerColor:Integer = 0
}

trait AutomaticPlayer2 extends AutomaticPlayer{
  override val playerColor:Integer = 1
}


