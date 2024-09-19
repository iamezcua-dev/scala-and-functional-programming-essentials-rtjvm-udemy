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
  
  //  logger.info(anotherFactorial(50000).toString)
  
  /**
   * Rule of thumb: When you need loops, use TAIL recursion whenever is possible.
   */
  
  /**
   * Exercises:
   *
   *  - 1. Concatenate a string n times
   *  - 2. IsPrime function tail recursive
   *  - 3. Fibonacci function, tail recursive.
   */
  // 1.
  private def repeatString(text: String, times: Int): String =
    @tailrec
    def repeatStringHelper(remaining: Int, accum: String): String =
      if (remaining <= 0) accum
      else repeatStringHelper(remaining - 1, accum + text)
    
    repeatStringHelper(times, "")
  
  assert(repeatString("EddieJobs", 3).equals("EddieJobsEddieJobsEddieJobs"))
  assert(repeatString("Rana", 10).equals("RanaRanaRanaRanaRanaRanaRanaRanaRanaRana"))
  assert(repeatString("Quechoni", 8).equals("QuechoniQuechoniQuechoniQuechoniQuechoniQuechoniQuechoniQuechoni"))
  
  // 2.
  private def isPrime(number: Int): Boolean = {
    @tailrec
    def isPrimeHelper(current: Int, isStillPrime: Boolean = true): Boolean = {
      if (!isStillPrime) false
      else if (current <= 1) true
      else isPrimeHelper(current - 1, isStillPrime && number % current != 0)
    }
    
    number >= 2 && isPrimeHelper(number / 2)
  }
  
  // Assertions for known prime numbers
  assert(isPrime(2), "2 should be prime")
  assert(isPrime(3), "3 should be prime")
  assert(isPrime(5), "5 should be prime")
  assert(isPrime(7), "7 should be prime")
  assert(isPrime(11), "11 should be prime")
  
  // Assertions for known non-prime numbers
  assert(!isPrime(0), "0 should not be prime")
  assert(!isPrime(1), "1 should not be prime")
  assert(!isPrime(4), "4 should not be prime")
  assert(!isPrime(6), "6 should not be prime")
  assert(!isPrime(9), "9 should not be prime")
  
  // 3.
  private def fibonacci(number: Int): Int = {
    @tailrec
    def fibonacciHelper(remaining: Int, a: Int, b: Int): Int = {
      if (remaining <= 1) b
      else fibonacciHelper(remaining - 1, b, a + b)
    }
    
    if (number < 0) 0
    else if (number <= 1) number
    else fibonacciHelper(number, 0, 1)
  }
  
  assert(fibonacci(-1) == 0, "-1 should return 0")
  assert(fibonacci(0) == 0)
  assert(fibonacci(1) == 1)
  assert(fibonacci(2) == 1)
  assert(fibonacci(3) == 2)
  assert(fibonacci(4) == 3)
  assert(fibonacci(5) == 5)
  assert(fibonacci(6) == 8)
  assert(fibonacci(7) == 13)
  assert(fibonacci(8) == 21)
  assert(fibonacci(9) == 34)
  
}
