package com.rockthejvm
package lectures.part2oop

import Exceptions.{MathCalculationException, OverflowException, PocketCalculator, UnderflowException}

import org.scalatest.wordspec.AnyWordSpec


class ExceptionsTest extends AnyWordSpec {
  case class Fixture(pocketCalculator: PocketCalculator = PocketCalculator())

  "In a PocketCalculator" when {

    "trying to sum two values" should {
      "fail with an OverflowException error, when both values are positives and the result is negative" in {
        assertThrows[OverflowException](Fixture().pocketCalculator.add(Int.MaxValue, 8))
      }

      "fail with an UnderflowException error, when both values are negatives and the result is positive" in {
        assertThrows[UnderflowException](Fixture().pocketCalculator.add(-8, Int.MinValue))
      }
    }


    // -28 to 28
    // 28 + 1 = -28
    "trying to subtract two values" should {
      "fail with an UnderflowException error, when the first value is negative, the second value is positive and the " +
        "result is positive" in {
        assertThrows[UnderflowException](Fixture().pocketCalculator.subtract(-7, Int.MaxValue))
      }

      "fail with an OverflowException error, when the first value is positive, the second value is negative and the " +
        "result is negative" in {
        assertThrows[OverflowException](Fixture().pocketCalculator.subtract(Int.MaxValue, -1))
      }
    }

    "trying to multiply two values" should {
      "fail with an OverflowException error, when both values are positive, but the result is smaller than the first value" in {
        assertThrows[OverflowException](Fixture().pocketCalculator.multiply(Int.MaxValue, 2))
      }

      "fail with an UnderflowException error, when any of the values is negative, but we get a positive result" in {
        assertThrows[UnderflowException](Fixture().pocketCalculator.multiply(Int.MinValue, 1))
        assertThrows[UnderflowException](Fixture().pocketCalculator.multiply(Int.MinValue, 2))
        assertThrows[UnderflowException](Fixture().pocketCalculator.multiply(Int.MinValue, 3))
        assertThrows[UnderflowException](Fixture().pocketCalculator.multiply(Int.MinValue, 4))
        assertThrows[UnderflowException](Fixture().pocketCalculator.multiply(Int.MinValue, 5))
        assertThrows[UnderflowException](Fixture().pocketCalculator.multiply(1, Int.MinValue))
        assertThrows[UnderflowException](Fixture().pocketCalculator.multiply(2, Int.MinValue))
        assertThrows[UnderflowException](Fixture().pocketCalculator.multiply(3, Int.MinValue))
        assertThrows[UnderflowException](Fixture().pocketCalculator.multiply(4, Int.MinValue))
        assertThrows[UnderflowException](Fixture().pocketCalculator.multiply(5, Int.MinValue))
      }
    }

    "trying to divide by zero" should {
      "fail with a MathCalculationException" in {
        assertThrows[MathCalculationException](Fixture().pocketCalculator.divide(8, 0))
        assertThrows[MathCalculationException](Fixture().pocketCalculator.divide(9, 0))
        assertThrows[MathCalculationException](Fixture().pocketCalculator.divide(10, 0))
        assertThrows[MathCalculationException](Fixture().pocketCalculator.divide(10000, 0))
        assertThrows[MathCalculationException](Fixture().pocketCalculator.divide(Int.MaxValue, 0))
      }
    }
  }

}
