package com.rockthejvm
package lectures.part1basics

import scala.annotation.tailrec
import scala.math.ceil

object Functions extends App {
  private def aFunction(a: String, b: Int): String = {
    a + " " + b
  }

  println(aFunction("hello", 3))

  private def aParameterlessFunction(): Int = 42

  println(aParameterlessFunction())
  println(aParameterlessFunction())

  private def aRepeatedFunction(aString: String, n: Int): String = {
    if (n == 1) aString
    else aString + "-" + aRepeatedFunction(aString, n - 1)
  }

  println(aRepeatedFunction("hello", 5))

  // When you need loops, use recursion.

  private def aFunctionWithSideEffects(aString: String): Unit = println(aString)

  private def aBigFunction(n: Int): Int = {
    def aSmallerFunction(a: Int, b: Int): Int = a + b

    aSmallerFunction(n, n - 1)
  }

  println(aBigFunction(5))

  // 1) A greeting function (name, age) => "Hi, my name is $name and I am $age years old."
  def greeting(name: String, age: Int): Unit = {
    println(s"Hi, my name is $name and I am $age years old.")
  }

  greeting("Isaac", 34)

  // 2) Factorial function 1 * 2 * 3 * ... * n
  def factorial(n: Int): BigInt = {
    @tailrec
    def factorialHelper(n: Int, accumulator: BigInt): BigInt = {
      if (n <= 1) accumulator
      else factorialHelper(n - 1, accumulator * n)
    }

    factorialHelper(n, 1)
  }


  // 3) A Fibonacci function:
  //        f(1) = 1
  //        f(2) = 1
  //        f(n) = f(n-1) + f(n-2)
  def fibonacci(n: Int): Int = {
    if (n <= 2) 1
    else fibonacci(n - 2) + fibonacci(n - 1)
  }

  /** fibonacci(1) = 1
   * fibonacci(2) = 1
   * fibonacci(3) = fibonacci(2) + fibonacci(1) = 1 + 1 = 2
   * fibonacci(4) = fibonacci(3) + fibonacci(2) = 2 + 1 = 3
   * fibonacci(5) = fibonacci(4) + fibonacci(3) = 3 + 2 = 5
   * fibonacci(6) = fibonacci(5) + fibonacci(4) = 5 + 3 = 8 13 21 34
   */

  // 4) Tests if a number ir prime.
  def isPrime(aNumber: Int): Boolean = {
    // A number is prime if and only if their only factors are 1 and itself.
    @tailrec
    def isPrimeHelper(currentNumber: Int = ceil(aNumber / 2d).toInt): Boolean = {
      if (currentNumber <= 1) true
      else (aNumber % currentNumber != 0) && isPrimeHelper(currentNumber - 1)
    }

    if (aNumber < 2) false
    else isPrimeHelper()
  }

}