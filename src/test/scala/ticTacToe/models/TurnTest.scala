package ticTacToe.models

import org.scalatest.FunSuite

class TurnTest extends FunSuite {

  test("take initial") {
    val turn = new Turn()
    assert(turn.take == 0)
  }

  test("take not initial") {
    val turn = new Turn(1)
    assert(turn.take == 1)
  }

  test("changed initial") {
    val turn = new Turn()
    assert(turn.changed.equals(new Turn(1)))
  }

  test("changed not initial") {
    val turn = new Turn(1)
    assert(turn.changed.equals(new Turn()))
  }

}
