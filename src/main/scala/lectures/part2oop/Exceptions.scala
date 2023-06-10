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
  def getInt(withExceptions: Boolean): Int =
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
}
