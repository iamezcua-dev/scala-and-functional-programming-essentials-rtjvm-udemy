package com.rockthejvm
package lectures.part2oop

object Exceptions extends App {
  val x: String = null
  //  println(x.length)
  // This line above will crash with NullPointerException

  // 1. throwing and catching exceptions
  //  val aWeirdValue: String = throw new NullPointerException()

  // throwable classes extends the Throwable class.
  // Exception and Error are the major Throwable subtypes.

  // 2. How to catch exceptions?
  private def getInt(withExceptions: Boolean): Int =
    if withExceptions then throw new RuntimeException("No Int for you!")
    else 42

  val potentialFail = try {
    // code that might throw
    getInt(true)
  } catch {
    case e: RuntimeException => println("Caught a Runtime exception")
  } finally {
    // code that will get executed no matter what.
    // this section is optional and does not influence the return type of this expression
    // Use finally only for side effects (logging)
    println("Finally!")
  }

  // 3. How to define you own exceptions?
  private class MyException extends Exception

  private val exception = new MyException
  //  throw exceptions

  /*
    1. Crash your program with an OOM exception.
    2. Crash with SOError
    3. PocketCalculator
      - add(x, y)
      - subtract(x, y)
      - multiply(x, y)
      - divide(x, y)

      Throw:
        - OverflowException if add(x, y) exceeds Int.MAX_VALUE
        - UnderflowException if subtract(x, y) exceeds Int.MIN_VALUE
        - MathCalculationException for division by 0
  */

  // 1. Crashing my program with an OOM exception
  try {
    val myArray = Array.ofDim[Int](Int.MaxValue)
  } catch {
    case e: OutOfMemoryError => println("OOM exception handled!")
    case _ => println(s"Unexpected exception was handled instead.")
  }

  // 2. Crashing my program with SOError
  private def infinite: Int = 1 + infinite

  try {
    val noLimit = infinite
  } catch {
    case e: StackOverflowError => println("SOE handled!")
    case _ => println(s"Unexpected exception was handled instead.")
  }

  // 3. Pocket Calculator
  class OverflowException extends Exception

  class UnderflowException extends Exception

  class MathCalculationException extends Exception

  class PocketCalculator {
    def add(x: Int, y: Int): Int = {
      val result = x + y
      if x > 0 && y > 0 && result < 0 then throw new OverflowException
      else if x < 0 && y < 0 && result > 0 then throw new UnderflowException
      else result
    }

    def subtract(x: Int, y: Int): Int = {
      val result = x - y
      if x < 0 && y > 0 && result > 0 then throw new UnderflowException
      else if x > 0 && y < 0 && result < 0 then throw new OverflowException
      result
    }

    def multiply(x: Int, y: Int): Int = {
      val result = x * y
      if (x > 0 && y > 0) && (result < 0 || (result < x || result < y)) then throw new OverflowException
      else if ((x < 0 && y > 0) && (result == 0 || result == x)) || ((x > 0 && y < 0) && (result == 0 || result == y)) then throw new UnderflowException
      else result
    }

    def divide(x: Int, y: Int): Int = {
      if y == 0 then throw new MathCalculationException
      x / y
    }
  }

  object PocketCalculator {
    def apply(): PocketCalculator = new PocketCalculator()
  }

  private val calc = PocketCalculator()
  private val a_sum = Int.MaxValue
  private val b_sum = 8
  private val a_sub = Int.MinValue
  private val b_sub = 10
  private val a_mult = 8
  private val b_mult = 0
  private val a_div = 9
  private val b_div = 0

  private lazy val sum = calc.add(a_sum, b_sum)
  private lazy val subtraction = calc.subtract(a_sub, b_sub)
  private lazy val multiplication = calc.multiply(a_mult, b_mult)
  private lazy val division = calc.divide(a_div, b_div)


  try println(sum)
  catch {
    case overflow: OverflowException => println(s"Summing up $a_sum to $b_sum would overflow Int capacity.")
    case underflow: UnderflowException => println(s"Subtracting $a_sub to $b_sub would underflow Int capacity.")
  }

  try println(subtraction)
  catch case underflow: UnderflowException => println(s"Subtracting $a_sub to $b_sub would underflow Int capacity.")

  try println(multiplication)
  catch case _ => println("Unexpected error occurred")

  try println(division)
  catch case zeroDivision: MathCalculationException => println(s"Dividing $a_div by $b_div would yield infinity.")

}
