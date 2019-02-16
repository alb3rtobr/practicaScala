package ticTacToe

import ticTacToe.models.Game
import ticTacToe.views.{GameView, CoordinateView, GestorIO}

object Main {

  var game = new Game

  def main(args: Array[String]): Unit = {

    GameView.write(game)
    do {
      if (!game.isComplete){
        game = game.put(CoordinateView.read)
      } else {
        game = game.move(CoordinateView.read, CoordinateView.read)
      }
      GameView.write(game)
    } while (!game.isTicTacToe)
    GestorIO.write("... pero has perdido")
  }
}
