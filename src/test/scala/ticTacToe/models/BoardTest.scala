package ticTacToe.models

import org.scalatest.FunSuite

class BoardTest extends FunSuite {

  test("put on 1,1 with x") {
    val board = new Board()
    val coordinate = new Coordinate(1, 1)
    val player = 0
    assert(board.put(coordinate, player) ==
      new Board(List(
        List(-1, -1, -1),
        List(-1, 0, -1),
        List(-1, -1, -1))))
  }

  test("getColor") {
    val board = new Board(List(
      List(-1, -1, -1),
      List(-1, 0, -1),
      List(-1, -1, 1)))
    assert(board.getColor(new Coordinate(0, 0)) == -1)
    assert(board.getColor(new Coordinate(1, 1)) == 0)
    assert(board.getColor(new Coordinate(2, 2)) == 1)
  }

  test("put on 0,2 with o") {
    val board = new Board(List(
      List(-1, -1, -1),
      List(-1, 0, -1),
      List(-1, -1, -1)))
    val coordinate = new Coordinate(0, 2)
    val player = 1
    assert(board.put(coordinate, player) ==
      new Board(List(
        List(-1, -1, 1),
        List(-1, 0, -1),
        List(-1, -1, -1))))
  }

  test("put on 2,0 with x") {
    val board = new Board(List(
      List(-1, -1, -1),
      List(-1, 0, -1),
      List(-1, -1, 1)))
    val coordinate = new Coordinate(2, 0)
    val player = 0
    assert(board.put(coordinate, player) ==
      new Board(List(
        List(-1, -1, -1),
        List(-1, 0, -1),
        List(0, -1, 1))))
  }

  test("move from 1,1 to 2,0 with x") {
    val board = new Board(List(
      List(-1, -1, -1),
      List(-1, 0, -1),
      List(-1, -1, 1)))
    val from = new Coordinate(1, 1)
    val to = new Coordinate(2, 0)
    assert(board.move(from, to).toString ==
      new Board(List(
        List(-1, -1, -1),
        List(-1, -1, -1),
        List(0, -1, 1))).toString)
  }

  test("complete") {
    assert(new Board(List(
      List(-1, 0, 1),
      List(-1, 0, -1),
      List(0, 1, 1))).isComplete)
    assert(!new Board(List(
      List(-1, 0, 1),
      List(-1, -1, -1),
      List(0, 1, 1))).isComplete)
  }

  test("isTicTacToe") {
    assert(new Board(List(
      List(0, 1, -1),
      List(-1, 0, -1),
      List(1, -1, 0))).isTicTacToe)
    assert(new Board(List(
      List(0, 1, -1),
      List(-1, 1, -1),
      List(0, 1, 0))).isTicTacToe)
    assert(new Board(List(
      List(0, -1, -1),
      List(1, 1, 1),
      List(0, -1, 0))).isTicTacToe)
    assert(!new Board(List(
      List(0, 1, -1),
      List(-1, -1, -1),
      List(1, -1, 0))).isTicTacToe)
  }

}
