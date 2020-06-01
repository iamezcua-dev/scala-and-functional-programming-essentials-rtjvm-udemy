package lectures.part2.oop

import java.util.concurrent.TimeUnit

import scala.math.BigInt
import scala.util.Random

object Exceptions extends App {
  /*
   * 1. How to throw exceptions?
   */
  val x: String = null
  //	println( x.length )
  // This ^ will crash with a NullPointerException

  // 1. Throwing exceptions
  // val aWeirdValue = throw new NullPointerException

  // Throwable classes extend the Throwable class
  // Exception and Error are the major Throwable subtypes
  // Exception: Denotes something that went wrong with the program.
  // Error: Denotes something that went wrong with the system.

  // 2. How to catch exceptions?
  val r = new Random(System.currentTimeMillis)

  def getInt(withExceptions: Boolean): Int = {
    if (withExceptions) throw new RuntimeException("No integer for you!")
    else r.nextInt
  }

  val potentialFail = try {
    getInt(true)
  } catch {
    case e: RuntimeException => {
      println(s"Runtime exception handled!\n  - Error message: ${e.getMessage}")
      42
    }
  } finally {
    // Code that will be executed no matter what.
    // The finally clause ...
    // 	- is optional.
    //  - does not influence the return ytype of this expression.
    //  - should be used only for side effects such as logging.
    println("This text will be printed no matter what.")
  }

  println(potentialFail)

  // 3. How to define your own exceptions?
  class MyException extends Exception()

  val exception = new MyException

  try {
    throw exception
  } catch {
    case exception: MyException => println(s"MyException caught!\n  - Error message: ${exception.getMessage}")
  }

  /*
    1. Crash your program with an OutOfMemoryError ( when you want to allocate more memory than the JVM has )
    2. Crash your program with a StackOverflowError
    3. PocketCalculator
      - add(x, y)
      - substract(x, y)
      - multiply(x, y)
      - divide(x, y)

      Throw:
        - OverflowException, if add(x, y) exceeds Int.MAX_VALUE
        - UnderflowException, if substract(x, y) exceeds Int.MIN_VALUE
        - MathCalculationException for division by 0
   */

  // My Solution to 1
  def threadCreator(maximumThreads: Int): Unit = {
    def threadCreatorHelper(threadCount: Int = 0): Unit = {
      if (threadCount <= maximumThreads) {
        val thread = new Thread(() => {
          //					println( s"Thread ${Thread.currentThread().getName} is sleeping during 2 seconds ..." )
          TimeUnit.SECONDS.sleep(10)
          //					println( s"Thread ${Thread.currentThread().getName} has woken up!" )
        })
        thread.setName(s"MyThread-$threadCount")
        thread.start()
        threadCreatorHelper(threadCount + 1)
      }
    }

    threadCreatorHelper()
  }

  try {
    threadCreator(300)
  } catch {
    case e: OutOfMemoryError => println(s"The new thread creation operation caused an out of memory error!")
  }

  // Solution to 2
  def factorial(number: Int): BigInt = {
    if (number <= 1) 1
    else number * factorial(number - 1)
  }

  val number = 20000
  try {
    println(factorial(number))
  } catch {
    case e: StackOverflowError => println(s"The number $number caused a stack overflow error!")
  }

  // Solution to 3
  /*
      3. PocketCalculator
        - add(x, y)
        - subtract(x, y)
        - multiply(x, y)
        - divide(x, y)

      Throw:
        - OverflowException, if add(x, y) exceeds Int.MAX_VALUE
        - UnderflowException, if substract(x, y) exceeds Int.MIN_VALUE
        - MathCalculationException for division by 0
   */
  trait Calculator[A, B] {
    def add(x: A, y: A): B

    def subtract(x: A, y: A): B

    def multiply(x: A, y: A): B

    def divide(x: A, y: A): Any
  }

  class OverflowException extends RuntimeException

  class UnderflowException extends RuntimeException

  class MathCalculationException extends RuntimeException("Division by zero")

  class PocketCalculator extends Calculator[Int, Int] {
    //		override def substract( x: Int, y: Int ): Int = ???
    //		override def multiply( x: Int, y: Int ): Int = ???
    //		override def divide( x: Int, y: Int ): Int = ???
    override def add(x: Int, y: Int): Int = {
      val sum = x + y
      if (x > 0 && y > 0 && sum < 0) throw new OverflowException
      else if (x < 0 && x < 0 && sum > 0) throw new UnderflowException
      else sum
    }

    override def subtract(x: Int, y: Int): Int = {
      val subtraction = x - y
      if (x > 0 && y < 0 && subtraction < 0) throw new OverflowException
      else if (x < 0 && y > 0 && subtraction > 0) throw new UnderflowException
      else subtraction
    }

    override def multiply(x: Int, y: Int): Int = {
      val multiplication = x * y
      if (x > 0 && y > 0 && multiplication < 0) throw new OverflowException
      else if (x < 0 && y < 0 && multiplication < 0) throw new OverflowException
      else if (x > 0 && y < 0 && multiplication > 0) throw new UnderflowException
      else if (x < 0 && y > 0 && multiplication > 0) throw new UnderflowException
      else multiplication
    }

    override def divide(x: Int, y: Int): Int = {
      if (y == 0) throw new MathCalculationException
      else x / y
    }
  }

  val pocketCalculator = new PocketCalculator
  try {
    println(s"The sum of 1 and 2 is: ${pocketCalculator.add(1, 2)}")
    println(s"The sum of 56 and 74 is: ${pocketCalculator.add(56, 74)}")
    println(s"The sum of 1 and ${Int.MaxValue} is: ${pocketCalculator.add(1, Int.MaxValue)}")
  } catch {
    case over: OverflowException => {
      println(s"The operation result exceeds ${Int.MaxValue}")
      TimeUnit.MILLISECONDS.sleep(250)
      over.printStackTrace()
    }
  }

  try {
    println(s"The subtraction of ${Int.MinValue} and 1 is: ${pocketCalculator.subtract(Int.MinValue, 1)}")
  } catch {
    case under: UnderflowException => {
      println(s"The operation result is lower than ${Int.MinValue}")
      TimeUnit.MILLISECONDS.sleep(250)
      under.printStackTrace()
    }
  }

  try {
    println(s"The division of 20 and 0 is: ${pocketCalculator.divide(20, 0)}")
  } catch {
    case math: MathCalculationException => {
      println("Division by zero!")
      TimeUnit.MILLISECONDS.sleep(250)
      math.printStackTrace()
    }
  }
}
