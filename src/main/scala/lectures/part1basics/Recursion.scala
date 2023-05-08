package com.rockthejvm
package lectures.part1basics

import scala.annotation.tailrec
import scala.math.ceil

object Recursion extends App {
  private def factorial(n: Int): Int = {
    if (n <= 1) 1
    else {
      println(s"Computing factorial of $n - I first need factorial of ${n - 1}")
      val result = n * factorial(n - 1)
      println(s"Computed factorial of $n")

      result
    }
  }


  private def anotherFactorial(n: Int): BigInt = {
    @tailrec
    def factorialHelper(x: Int, accumulator: BigInt): BigInt = {
      if (x <= 1) accumulator
      else factorialHelper(x - 1, x * accumulator)
    }

    factorialHelper(n, 1)
  }

  //  println(anotherFactorial(50000))

  /*
    1. Concatenate a string n times.
    2. IsPrime function tail recursive.
    3. Fibonacci function, tail recursive.
  */

  // 1. Concatenate a string n times.
  def concatenateStringTailrec(times: Int, word: String): String = {
    @tailrec
    def concatenateStringHelper(remaining: Int, concatenated: StringBuilder): String =
      if (remaining <= 0) concatenated.toString
      else concatenateStringHelper(remaining - 1, concatenated.append(word))

    concatenateStringHelper(times, new StringBuilder())
  }

  println(concatenateStringTailrec(3, "Hello, Scala"))

  // 2. IsPrime function tail recursive.
  def isPrimeTailrec(n: Int): Boolean = {
    @tailrec
    def isPrimeHelper(nextNumber: Int = ceil(n / 2d).toInt): Boolean = {
      if nextNumber <= 1 then true
      else (n % nextNumber != 0) && isPrimeHelper(nextNumber - 1)
    }

    if n < 2 then false
    else isPrimeHelper()
  }

  // 3. Fibonacci function, tail recursive.
  def fibonacciTailrec(n: Int): BigInt = {
    @tailrec
    def fibonacciTailrecHelper(current: Int, accumA: BigInt, accumB: BigInt): BigInt = {
      if current >= n then accumB
      else fibonacciTailrecHelper(current + 1, accumB, accumA + accumB)
    }

    if n <= 0 then 0
    else fibonacciTailrecHelper(1, 0, 1)
  }

}
