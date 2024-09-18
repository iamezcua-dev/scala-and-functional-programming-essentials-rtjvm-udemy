package com.rockthejvm
package lectures.part1.basics

import com.typesafe.scalalogging.LazyLogging

import scala.annotation.tailrec

object Recursion extends App with LazyLogging {
  def factorial(n: Int): Int =
    if (n <= 1) 1
    else {
      logger.info(s"Computing factorial of $n - I first need factorial of ${n - 1}")
      val result = n * factorial(n - 1)
      logger.info(s"Computed factorial of $n")
      result
    }
  
  // println(factorial(50000))
  // - The function above would fail, since it is using stack recursion, which quickly exhaust the stack memory
  // available, causing StackOverflowError errors in your code done in similar ways.
  
  def anotherFactorial(n: BigInt): BigInt = {
    @tailrec
    def factorialHelper(x: BigInt, accumulator: BigInt): BigInt =
      if (x <= 1) accumulator
      else {
        logger.debug(s"Computing factorial of $n - Processing $x ...")
        factorialHelper(x - 1, x * accumulator)
      }
    
    factorialHelper(n, 1)
  }
  
  logger.info(anotherFactorial(50000).toString)
  
  /**
   * Rule of thumb: When you need loops, use TAIL recursion whenever is possible.
   */
}
