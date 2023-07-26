package com.rockthejvm
package lectures.part2oop

import lectures.part2oop.Exceptions.{MathCalculationException, OverflowException, PocketCalculator, UnderflowException}

import org.scalatest.BeforeAndAfterAll
import org.scalatest.wordspec.AnyWordSpec

import scala.collection.mutable.ListBuffer

class ExceptionsTest extends AnyWordSpec {
  case class Fixture(pocketCalculator: PocketCalculator = PocketCalculator())

  "In a PocketCalculator" when {

    "trying to sum two values and the result exceeds the maximum handled integer" should {
      "fail with an OverflowException error" in {
        assertThrows[OverflowException](Fixture().pocketCalculator.add(Int.MaxValue, 8))
      }
    }

    "trying to subtract two values expecting a negative value that exceeds the maximum handled integer" should {
      "fail with an UnderflowException error" in {
        assertThrows[UnderflowException](Fixture().pocketCalculator.subtract(Int.MinValue, 10))
      }
    }

    "trying to divide by zero" should {
      "fail with a MathCalculationException" in {
        assertThrows[MathCalculationException](Fixture().pocketCalculator.divide(8, 0))
      }
    }
  }

}
